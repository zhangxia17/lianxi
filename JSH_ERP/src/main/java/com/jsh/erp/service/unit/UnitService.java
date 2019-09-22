package com.jsh.erp.service.unit;

import com.alibaba.fastjson.JSONObject;
import com.jsh.erp.constants.BusinessConstants;
import com.jsh.erp.datasource.entities.Unit;
import com.jsh.erp.datasource.entities.UnitExample;
import com.jsh.erp.datasource.entities.User;
import com.jsh.erp.datasource.mappers.UnitMapper;
import com.jsh.erp.datasource.mappers.UnitMapperEx;
import com.jsh.erp.service.log.LogService;
import com.jsh.erp.service.user.UserService;
import com.jsh.erp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class UnitService {
    private Logger logger = LoggerFactory.getLogger(UnitService.class);

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private UnitMapperEx unitMapperEx;
    @Resource
    private UserService userService;
    @Resource
    private LogService logService;

    public Unit getUnit(long id) {
        return unitMapper.selectByPrimaryKey(id);
    }

    public List<Unit> getUnit() {
        UnitExample example = new UnitExample();
        return unitMapper.selectByExample(example);
    }

    public List<Unit> select(String name, int offset, int rows) {
        return unitMapperEx.selectByConditionUnit(name, offset, rows);
    }

    public Long countUnit(String name) {
        return unitMapperEx.countsByUnit(name);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertUnit(String beanJson, HttpServletRequest request) {
        Unit unit = JSONObject.parseObject(beanJson, Unit.class);
        return unitMapper.insertSelective(unit);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUnit(String beanJson, Long id) {
        Unit unit = JSONObject.parseObject(beanJson, Unit.class);
        unit.setId(id);
        return unitMapper.updateByPrimaryKeySelective(unit);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteUnit(Long id) {
        return unitMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUnit(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        UnitExample example = new UnitExample();
        example.createCriteria().andIdIn(idList);
        return unitMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        UnitExample example = new UnitExample();
        example.createCriteria().andIdNotEqualTo(id).andUnameEqualTo(name).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Unit> list = unitMapper.selectByExample(example);
        return list.size();
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUnitByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_UNIT,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return unitMapperEx.batchDeleteUnitByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }


}
