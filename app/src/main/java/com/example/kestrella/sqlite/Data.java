package com.example.kestrella.sqlite;

/**
 * Created by kestrella on 1/15/16.
 * accessor and mutator
 */
public class Data {
    private int _id;
    private String _name;

    public Data(int _id, String _name){
        this.setId(_id);
        this.setName(_name);
    }

    public void setId(int _id) {
        this._id = _id;
    }
    public int getId() {
        return _id;
    }

    public void setName(String _name) {
        this._name = _name;
    }
    public String getName() {
        return _name;
    }

}
