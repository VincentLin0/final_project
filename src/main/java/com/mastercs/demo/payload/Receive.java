package com.mastercs.demo.payload;


import lombok.Data;

@Data
public class Receive extends PageInfo {
    // 接收来自前端的输出
    String searchContent;

}
