package ro.sci.h11;

import org.junit.*;
import ro.sci.h11.db.BookingsDbException;
import ro.sci.h11.model.Accomodation;
import ro.sci.h11.model.RoomFair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the DataBase tests.
 *
 * @author Emanuel Pruker
 */
public class DbTest {

    private TestBookingsDb db;
    private List<Accomodation> accomodations = new ArrayList<>();

    @BeforeClass
    public static void initTests() throws BookingsDbException, SQLException {
        TestBookingsDb.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingsDbException, SQLException {
        TestBookingsDb.dropTestDB();
    }

    @Before
    public void setUp() {
        db = new TestBookingsDb();
    }

    @After
    public void tearDown() throws BookingsDbException, SQLException {
        db.dropDataFromTables();
        accomodations.clear();
    }

    @Test
    public void whenNewAccomodationInsertedIntoDB_getReturnsThem() throws BookingsDbException, SQLException {
        Accomodation accomodation = getTestData().get(0);
        insertData(accomodation);
        Accomodation qurriedAccomodation = queryData().get(0);

        Assert.assertEquals(accomodation.getType(), qurriedAccomodation.getType());
        Assert.assertEquals(accomodation.getBedType(), qurriedAccomodation.getBedType());
        Assert.assertEquals(accomodation.getMaxGuests(), qurriedAccomodation.getMaxGuests());
        Assert.assertEquals(accomodation.getDescription(), qurriedAccomodation.getDescription());
        Assert.assertEquals(accomodation.getRoomFair().getValue(), qurriedAccomodation.getRoomFair().getValue(), 0.02);
        Assert.assertEquals(accomodation.getRoomFair().getSeason(), qurriedAccomodation.getRoomFair().getSeason());
    }

    @Test
    public void whenNewAccomodationsInsertedIntoDB_getReturnsThem() throws BookingsDbException, SQLException {
        List<Accomodation> accomodations = getTestData();
        insertData(accomodations.get(0));
        insertData(accomodations.get(1));
        Accomodation qurriedAccomodation = queryData().get(0);
        Accomodation qurriedAccomodation2 = queryData().get(1);

        Assert.assertEquals(accomodations.get(0), qurriedAccomodation);
        Assert.assertEquals(accomodations.get(1), qurriedAccomodation2);
    }

    private void insertData(Accomodation accomodation) throws SQLException, BookingsDbException {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        try (Connection conn = db.connect()) {
            ps1 = conn.prepareStatement("INSERT INTO accomodation(type, bed_type, max_guests, description) values (?, ?, ?, ?)");
            ps1.setString(1, accomodation.getType());
            ps1.setString(2, accomodation.getBedType());
            ps1.setInt(3, accomodation.getMaxGuests());
            ps1.setString(4, accomodation.getDescription());
            ps1.executeUpdate();

            ps2 = conn.prepareStatement("INSERT INTO room_fair (value, season) values(?, ?)");
            ps2.setDouble(1, accomodation.getRoomFair().getValue());
            ps2.setString(2, accomodation.getRoomFair().getSeason());
            ps2.executeUpdate();

            Statement statement1 = conn.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("SELECT CURRVAL('accomodation_ids')");
            resultSet1.next();
            int accomodationId = resultSet1.getInt(1);

            Statement statement2 = conn.createStatement();
            ResultSet resultSet2 = statement2.executeQuery("SELECT CURRVAL('room_fair_ids')");
            resultSet2.next();
            int roomFairId = resultSet2.getInt(1);

            ps3 = conn.prepareStatement("INSERT INTO accomodation_fair_relation (id_accomodation, id_room_fair) VALUES(?, ?)");
            ps3.setInt(1, accomodationId);
            ps3.setInt(2, roomFairId);
            ps3.executeUpdate();

        } finally {
            try {
                if (ps1 != null) {
                    ps1.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    private List<Accomodation> getTestData() {
//        List<Accomodation> accomodations = new ArrayList<>();
        Accomodation accomodation = new Accomodation();
        Accomodation accomodation2 = new Accomodation();
        RoomFair roomFair = new RoomFair();
        RoomFair roomFair2 = new RoomFair();
        accomodation.setRoomFair(roomFair);
        accomodation2.setRoomFair(roomFair2);
        accomodation.setId(1);
        accomodation2.setId(2);
        accomodation.setType("Double");
        accomodation2.setType("Single");
        accomodation.setBedType("Double");
        accomodation2.setBedType("Single");
        accomodation.setMaxGuests(2);
        accomodation2.setMaxGuests(1);
        accomodation.setDescription("Description of the double room");
        accomodation2.setDescription("Description of the single room");
        roomFair.setValue(200);
        roomFair2.setValue(150);
        roomFair.setSeason("Spring");
        roomFair2.setSeason("Summer");
        accomodations.add(accomodation);
        accomodations.add(accomodation2);
        return accomodations;
    }

    private List<Accomodation> queryData() throws BookingsDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT a.*, r.value, r.season FROM accomodation a\n" +
                    "JOIN accomodation_fair_relation rel ON rel.id_accomodation = a.id\n" +
                    "JOIN room_fair r on rel.id_room_fair = r.id;");
            ArrayList<Accomodation> accomodations = new ArrayList<>();
            while (resultSet.next()) {
                Accomodation accomodation = mapResultSetToAccomodation(resultSet);
                accomodations.add(accomodation);
            }

            return accomodations;
        }
    }

    private Accomodation mapResultSetToAccomodation(ResultSet resultSet) throws SQLException {
        Accomodation accomodation = new Accomodation();
        RoomFair roomFair = new RoomFair();
        accomodation.setRoomFair(roomFair);
        accomodation.setId(resultSet.getInt("id"));
        accomodation.setType(resultSet.getString("type"));
        accomodation.setBedType(resultSet.getString("bed_type"));
        accomodation.setMaxGuests(resultSet.getInt("max_guests"));
        accomodation.setDescription(resultSet.getString("description"));
        roomFair.setValue(resultSet.getDouble("value"));
        roomFair.setSeason(resultSet.getString("season"));
        return accomodation;
    }
}