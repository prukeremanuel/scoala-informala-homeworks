package ro.sci.h11.model;

/**
 * This class represents the model of an accommodation.
 *
 * @author Emanuel Pruker
 */
public class Accomodation {

    private int id;
    private String type;
    private String bedType;
    private int maxGuests;
    private String description;
    private RoomFair roomFair;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomFair getRoomFair() {
        return roomFair;
    }

    public void setRoomFair(RoomFair roomFair) {
        this.roomFair = roomFair;
    }

    @Override
    public String toString() {
        return "Accomodation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bedType='" + bedType + '\'' +
                ", maxGuests=" + maxGuests +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accomodation)) return false;

        Accomodation that = (Accomodation) o;

        if (id != that.id) return false;
        if (maxGuests != that.maxGuests) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (bedType != null ? !bedType.equals(that.bedType) : that.bedType != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (bedType != null ? bedType.hashCode() : 0);
        result = 31 * result + maxGuests;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
