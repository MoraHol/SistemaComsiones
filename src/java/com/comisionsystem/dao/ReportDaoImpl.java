package com.comisionsystem.dao;

import com.comisionsystem.model.Report;
import com.comsionsystem.idao.IReportDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public class ReportDaoImpl extends ConnectionSQL implements IReportDao {

    @Override
    public ArrayList<Report> findAll() {
        ArrayList<Report> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `reports`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(this.findReportById(rs.getInt("id_report")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Report findReportById(int id) {
        Report report = new Report();
        try {
            this.connect();
            String query = "SELECT * FROM `reports` WHERE id_report = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                report.setId(rs.getInt(t++));
                report.setFile(rs.getBytes(t++));
                report.setMonth(rs.getString(t++));
                report.setYear(rs.getString(t++));                
            }
            this.disconnect();
        } catch (Exception e) {
        }
        return report;
    }

    @Override
    public int save(Report report) {
        int result = 0;
        try {
            this.connect();
            String query = "INSERT INTO `reports` (`id_report`, `report_file`, `month`, `year`) VALUES (NULL, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setBytes(1, report.getFile());
            pstm.setString(2, report.getMonth());
            pstm.setString(3, report.getYear());
            result = pstm.executeUpdate();
            
            this.disconnect();
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try {
            this.connect();
            String query = "DELETE FROM `reports` WHERE `reports`.`id_report` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            result = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int update(Report report) {
        int result = 0;
        try {
            this.connect();
            String query = "UPDATE `reports` SET `report_file` = ?, `month` = ?, `year` = ? WHERE `reports`.`id_report` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setBytes(1, report.getFile());
            pstm.setString(2, report.getMonth());
            pstm.setString(3, report.getYear());
            
            result = pstm.executeUpdate();          
            this.disconnect();
        }catch(Exception e){
            
        }
        return result;
    }
}
