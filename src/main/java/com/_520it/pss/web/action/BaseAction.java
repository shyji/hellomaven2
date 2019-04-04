package com._520it.pss.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {
    public static final String LIST="list";
    public static final String VIEW = "view";
    public static final String PAGE_RESULT = "pageResult";
    public static final String NO_PERMISSION = "nopermission";
    public static final String JSON = "jsonResult";


    public void prepare() throws Exception {

    }
}
