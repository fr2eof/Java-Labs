package Server.src.main.java.se.ifmo.ru;

import Common.src.main.java.se.ifmo.ru.elements.Coordinates;
import Common.src.main.java.se.ifmo.ru.elements.Location;
import Common.src.main.java.se.ifmo.ru.elements.Person;
import Common.src.main.java.se.ifmo.ru.elements.Worker;
import Common.src.main.java.se.ifmo.ru.enums.EColor;
import Common.src.main.java.se.ifmo.ru.enums.HColor;
import Common.src.main.java.se.ifmo.ru.enums.Position;
import Common.src.main.java.se.ifmo.ru.enums.Status;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
//todo пул соединений с БД
//todo не хранить колекцию в памяти
public class DataBaseManager {
    Connection connection;

    public DataBaseManager() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/studs";
        String user = "s412952";
        String password = "xY2D3h4Sho69LfID";
        //todo тянуть из кфг
        connection = DriverManager.getConnection(url, user, password);
    }


    public CopyOnWriteArrayList<Worker> loadFromDB() {
        CopyOnWriteArrayList<Worker> col = new CopyOnWriteArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM objects;\n")) {
            ResultSet updatedRows = statement.executeQuery();
            if (updatedRows.next()) {
                Worker worker = new Worker();
                worker.setUser(updatedRows.getString(1));
                worker.setId(Integer.parseInt(updatedRows.getString(2)));
                worker.setName(updatedRows.getString(3));
                worker.setCreationDate(updatedRows.getString(4));
                worker.setStartDate(String.valueOf(ZonedDateTime.parse(updatedRows.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX[V]"))));
                worker.setPosition(Position.valueOf(updatedRows.getString(6)));
                worker.setStatus(Status.valueOf(updatedRows.getString(7)));
                worker.setSalary(Integer.valueOf(updatedRows.getString(8)));
                worker.setCoordinates(new Coordinates(Float.valueOf(updatedRows.getString(9)), Integer.valueOf(updatedRows.getString(10))));
                worker.setPerson(new Person(updatedRows.getString(11), EColor.valueOf(updatedRows.getString(12)), HColor.valueOf(updatedRows.getString(13)), new Location(Long.valueOf(updatedRows.getString(14)), Long.valueOf(updatedRows.getString(15)), updatedRows.getString(16))));
                worker.setLocation(new Location(Long.valueOf(updatedRows.getString(14)), Long.valueOf(updatedRows.getString(15)), updatedRows.getString(16)));
                col.add(worker);
            }
            return col;
        } catch (SQLException e) {
            log.error("[DATA BASE MANAGER] Exception caught: " + e.getMessage());
        }
        return col;
    }

    public boolean login(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE login = ? AND password = ?;"
        )) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            log.error("[DATA BASE MANAGER]: Login & password matching: " + ex.getMessage());
            return false;
        }
        return false;
    }

    public boolean register(String login, String password) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, password) VALUES (?,?);")) {
            statement.setString(1, login);
            statement.setString(2, password);
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException ex) {
            log.error("[DATA BASE MANAGER]: Registration " + ex.getMessage());
            return false;
        }
    }


    public boolean addWorker(Worker worker) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO objects (" +
                "userLogin, workerId, name, creationDate, startDate, position, status, salary, x, y, person_passportId, person_eyeColor," +
                "person_hairColor, location_x, location_y, location_name" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, worker.getUser());
            statement.setInt(2, worker.getId());
            statement.setString(3, worker.getName());

            statement.setString(4, String.valueOf(worker.getCreationDate()));
            statement.setString(5, String.valueOf(worker.getStartDate()));
            statement.setString(6, String.valueOf(worker.getPosition()));
            statement.setString(7, String.valueOf(worker.getStatus()));
            statement.setInt(8, worker.getSalary());
            statement.setFloat(9, worker.getCoordinates().getX());
            statement.setInt(10, worker.getCoordinates().getY());
            statement.setString(11, worker.getPerson().getPassportID());
            statement.setString(12, String.valueOf(worker.getPerson().getEyeColor()));
            statement.setString(13, String.valueOf(worker.getPerson().getHairColor()));
            statement.setLong(14, worker.getPerson().getLocation().getX());
            statement.setLong(15, worker.getPerson().getLocation().getY());
            statement.setString(16, worker.getPerson().getLocation().getName());


            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException ex) {
            log.error("[DATA BASE MANAGER] Exception caught: " + ex.getMessage());
            return false;
        }
    }


    public boolean addIfMax(Worker worker, Integer salary) {

        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(salary) AS max_salary\n" +
                "FROM objects;\n")) {
            ResultSet updatedRows = statement.executeQuery();
            if (updatedRows.next()) {
                int maxSalary = updatedRows.getInt("salary");
                if (salary > maxSalary) {
                    return addWorker(worker);
                }
                return false;
            }
        } catch (SQLException e) {
            log.error("[DATA BASE MANAGER] Exception caught: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean removeFirst() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM objects\n" +
                "WHERE workerid = (SELECT MIN(workerid) FROM objects);\n")) {
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            log.error("[DATA BASE MANAGER] Exception caught: " + e.getMessage());
            return false;
        }
    }

    public boolean removeById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM objects\n" +
                "WHERE workerid = value;\n")) {
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            log.error("[DATA BASE MANAGER] Exception caught: " + e.getMessage());
            return false;
        }
    }

    public boolean removeAnuSalary(int salary) {
        try (PreparedStatement statement = connection.prepareStatement("WITH target_row AS (\n" +
                "    SELECT workerid\n" +
                "    FROM objects\n" +
                "    WHERE salary < value\n" +
                "    LIMIT 1\n" +
                ")\n" +
                "DELETE FROM objects\n" +
                "WHERE objects.workerid IN (SELECT target_row.workerid FROM target_row);\n")) {
            statement.setInt(1, salary);
            int updatedRows = statement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            log.error("[DATA BASE MANAGER] Exception caught: " + e.getMessage());
            return false;
        }
    }
}//делать по треду