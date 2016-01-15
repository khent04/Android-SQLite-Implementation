package com.example.kestrella.sqlite;

/**
 * Created by kestrella on 1/15/16.
 * mutator and accessor
 */
public class Data {
    private int _id;
    private String _name;
    private int _age;

    public Data(int _id, String _name, int _age){
        this.setId(_id);
        this.setName(_name);
        this.setAge(_age);
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

    public void setAge(int _age){
        this._age = _age;
    }

    public int getAge(){
        return _age;
    }

}
