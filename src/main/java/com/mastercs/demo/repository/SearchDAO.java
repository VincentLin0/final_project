package com.mastercs.demo.repository;

import com.mastercs.demo.bean.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchDAO extends JpaRepository<Search,Integer>{

    // 添加功能，按照关键字查询title ，这里一定要把 * 用上不然出问题，猜想可能是和主键设置有关？
    //新建一个查询，名字叫findByTitle，查询语句是后面那句
    @Query(name="findByTitle", nativeQuery = true, value="select * from knowledge where title LIKE %:title% or description LIKE %:title%")  //items是数据库baidu_search下的表items，存网页搜索结果
    List<Search> findByTitle(@Param("title") String title); //引用title,对数据库进行查询
}

