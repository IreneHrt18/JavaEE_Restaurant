package DAO;

import Model.History;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HistoryDAOI {
    public boolean addHistory(History history);
    public  boolean delHistory(History history);
    public boolean alterHistory(History history);
    public ArrayList<History> selHistory() throws SQLException;

}
