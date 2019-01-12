package org.toktakprogramming.cuetconnect1;

public class BloodItem {

    String Name;
    String bloodGroup;
    String Id;

    public BloodItem(String name, String bloodGroup, String id) {
        this.Name = name;
        this.bloodGroup = bloodGroup;
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
