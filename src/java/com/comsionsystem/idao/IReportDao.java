package com.comsionsystem.idao;

import com.comisionsystem.model.Report;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public interface IReportDao {
    public ArrayList<Report> findAll();
    public Report findReportById(int id);
    public int save(Report report);
    public int delete(int id);
    public int update(Report report);
}
