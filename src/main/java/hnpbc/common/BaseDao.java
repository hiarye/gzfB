package hnpbc.common;

import hnpbc.common.mapper.provider.MyProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface BaseDao<T> extends Mapper<T>,MySqlMapper<T> {
    @InsertProvider(
            type = MyProvider.class,
            method = "dynamicSQL"
    )
    int insertListIncludeId(List<T> var1);
}
