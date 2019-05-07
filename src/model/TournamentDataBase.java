package model;

import java.util.ArrayList;
import java.util.List;

public class TournamentDataBase {
	private List<Tournament> recordList;
	
	public TournamentDataBase() {
		this.recordList = new ArrayList<>();
	}
	
	public void addRecord(Tournament record) {
        recordList.add(record);
    }
	
	public void reInitDataBase(List<Tournament> listData) {
        for (Tournament tournament : listData) {
            addRecord(tournament);
        }
    }
	public List<Tournament> getRecordList() {
        return recordList;
    }
	
	public void setRecordList(List<Tournament> listData) {
		this.recordList = new ArrayList<>(listData);
	}
	
	public void removeAllTournaments() {
        recordList.clear();
    }
	
}
