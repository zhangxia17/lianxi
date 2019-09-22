package com.jsh.erp.service.unit;

import com.jsh.erp.service.ICommonQuery;
import com.jsh.erp.service.app.AppResource;
import com.jsh.erp.utils.Constants;
import com.jsh.erp.utils.QueryUtils;
import com.jsh.erp.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service(value = "unit_component")
@UnitResource
public class UnitComponent implements ICommonQuery {

    @Resource
    private UnitService unitService;

    @Override
    public Object selectOne(String condition) {
        return null;
    }

    @Override
    public List<?> select(Map<String, String> map) {
        return getUnitList(map);
    }

    private List<?> getUnitList(Map<String, String> map) {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        String order = QueryUtils.order(map);
        return unitService.select(name, QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map) {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        return unitService.countUnit(name);
    }

    @Override
    public int insert(String beanJson, HttpServletRequest request) {
        return unitService.insertUnit(beanJson, request);
    }

    @Override
    public int update(String beanJson, Long id) {
        return unitService.updateUnit(beanJson, id);
    }

    @Override
    public int delete(Long id) {
        return unitService.deleteUnit(id);
    }

    @Override
    public int batchDelete(String ids) {
        return unitService.batchDeleteUnit(ids);
    }

    @Override
    public int checkIsNameExist(Long id, String name) {
        return unitService.checkIsNameExist(id, name);
    }

}
