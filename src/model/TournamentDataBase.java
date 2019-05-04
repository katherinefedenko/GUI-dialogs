package model;

import java.util.ArrayList;
import java.util.List;

public class TournamentDataBase {
	private List<Tournament> recordList;
	
	public TournamentDataBase() {
		recordList = new ArrayList<>();
	}
	
	public void addRecord(Tournament record) {
        recordList.add(record);
    }
	
	public List<Tournament> getRecordOfTournaments() {
        return recordList;
    }
	
	public void DataBase(List<Tournament> listData) {
        for (Tournament tournament : listData) {
            addRecord(tournament);
        }
    }
	
	public void deleteRecords() {
        recordList.clear();
    }
	
}
