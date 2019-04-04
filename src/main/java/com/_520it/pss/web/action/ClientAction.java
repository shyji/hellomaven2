package com._520it.pss.web.action;

import com._520it.pss.domain.Client;
import com._520it.pss.query.ClientQueryObject;
import com._520it.pss.service.IClientService;
import com._520it.pss.uitl.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.sun.net.httpserver.Authenticator;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ClientAction extends BaseAction{
    @Setter
    private IClientService clientService;
    @Setter@Getter
    private Client client=new Client();
    @Getter
    private ClientQueryObject qo = new ClientQueryObject();
    @Getter@Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("客户对象列表")@InputConfig(methodName="input")
    public String execute() throws Exception {
        ActionContext.getContext().put(PAGE_RESULT,clientService.query(qo));
        return LIST;
    }
    @RequiredPermission("删除客户对象")
    public String delete() throws Exception{

        try {
            if(client.getId()!=null){
                clientService.delete(client);
                super.addActionMessage("删除成功!");
            }
        } catch (Exception e) {
            super.addActionError("删除失败!原因:"+e.getMessage());
            e.printStackTrace();
        }

        return SUCCESS;
    }

    @RequiredPermission("新增/修改客户对象")
    public String input() throws Exception {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
        return INPUT;
    }

    @RequiredPermission("保存客户对象")
    public String saveOrUpdate() {
        try {
            if (client.getId() != null) {
                clientService.update(client);
                super.addActionMessage("更新成功");
            } else {
                clientService.save(client);
                super.addActionMessage("保存成功");
            }
        } catch (Exception e) {
            super.addActionError(e.getMessage());
        }
        return SUCCESS;
    }
    @RequiredPermission("批量删除客户对象")
    public String batchDelete() throws Exception{
        if(ids.size()>0){
            clientService.batchDelete(ids);
        }
        return NONE;
    }

    public void prepareSaveOrUpdate() {
        if (client.getId() != null) {
            client = clientService.get(client.getId());
        }
    }

}
