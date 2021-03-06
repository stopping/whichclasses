package com.whichclasses.scraper;

import java.util.Map;

import com.google.inject.Inject;
import com.whichclasses.model.Course;
import com.whichclasses.model.DataSource;
import com.whichclasses.model.Department;
import com.whichclasses.model.DeptList;
import com.whichclasses.model.TceClass;
import com.whichclasses.scraper.page.DeptListPage;

public class Scraper implements DataSource {

  private final DeptList mDeptList;

  @Inject
  public Scraper(DeptListPage deptListPage) {
    this.mDeptList = deptListPage;
  }

  public void runScrape() {
    // For now: scrape one thing and build models for each.
    Map<String, Department> depts = mDeptList.getChildren();
    Department firstDepartment = depts.get("C_SC");
    System.out.println("Got department " + firstDepartment);
    Map<String, Course> courses = firstDepartment.getChildren();

    for (Course oneCourse : courses.values()) {
      System.out.println("Got course " + oneCourse);
      Map<String, TceClass> classes = oneCourse.getChildren();
      for (TceClass oneClass : classes.values()) {
        System.out.println("Got class " + oneClass);
        //System.out.println("Class: " + oneClass.getModel().toString());
      }
    }

  }

  @Override
  public DeptList getDepartmentList() {
    return mDeptList;
  }
  
}
