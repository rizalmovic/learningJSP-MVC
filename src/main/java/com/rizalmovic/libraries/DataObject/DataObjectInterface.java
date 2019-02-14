package com.rizalmovic.libraries.DataObject;

import java.util.List;

interface DataObjectInterface {
    public DataObject findById(int id);
    public List<DataObject> findAll();
    public boolean update(int id);
    public boolean save(int id);
    public boolean delete(int id);
}