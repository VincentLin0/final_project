import request from "@/utils/request";

const create = {
  data() {
    return {
      api: {
        listUrl: "",
        saveUrl: "",
        delUrl: "",
      },
      tableData: [],
      // 全局弹窗对话框
      isDrawerDialog: false,
      // 删除提示框
      isMessageBox: false,
      // 删除数据
      delArr: [],
      isNoData: false,
      // isShowLoding: true,
      formData: {},
      list: [
        {
          id: 1,
          title: "Java管理系统",
        },
      ],
      pageInfo: {
        pageSize: 10, // 每页10条数据
        pageNum: 1, // 当前页
        total: 0, // 总条数
      },
      // isClearContent:false,
      // 搜索参数
      searchParams: {},
      // 合并查询
      searchMerge: {},
      statusList: [
        {
          value: "启用",
        },
        {
          value: "禁用",
        },
      ],
      roleTypeList: [
        {
          value: "ROLE_ADMIN",
        },
        {
          value: "ROLE_USER",
        },
      ],
    };
  },
  created() {
    // 很多问题都是和执行顺序有关
    this.searchMerge = Object.assign(this.pageInfo, this.searchParams);
    try {
      this.getList();
    } catch (e) {
      console.log("getList方法不存在");
      console.log(e);
    }
  },
  methods: {
    // 控制每页页数
    handleSizeChange(val) {
      console.log(` ${val} each page`);
      this.pageInfo.pageSize = val;
      this.searchMerge = Object.assign(this.pageInfo, this.searchParams);
      this.getList();
    },
    // 控制页面切换
    handleCurrentChange(val) {
      console.log(`current page: ${val}`);
      this.pageInfo.pageNum = val;
      this.searchMerge = Object.assign(this.pageInfo, this.searchParams);
      console.log(this.searchMerge);
      this.getList();
    },
    closeDrawer(e) {
      this.isDrawerDialog = e;
    },
    // 编辑删除操作
    handleEdit(i, item) {
      console.log(i);
      this.isDrawerDialog = true;
      this.formData = item;
    },
    handleDelete(item) {
      this.delArr = [];
      this.delArr.push(item.id);
      /*            console.log(i)
                        console.log(item)*/
      // this.isMessageBox = true
      this.delMessage();
    },
    selectionChange(data) {
      console.log(data);
      this.delArr = [];
      data.forEach((item) => {
        this.delArr.push(item.id);
      });
    },
    delAll() {
      // this.isMessageBox = true
      this.delMessage();
    },

    createDrawer() {
      this.isDrawerDialog = true;
      this.formData = {};
    },
    search() {
      this.searchMerge = Object.assign(this.pageInfo, this.searchParams);
      this.getList();
    },
    resetForm() {
      this.searchParams = {};
    },
    submitDrawer(e) {
      console.log("获取提交的数据", e);
      this.isDrawerDialog = false;
      this.formData = Object.assign({}, e);
      this.saveForm();
    },
    delMessage() {
      this.$confirm(
        `This will permanently delete the data of id:<span style="color:red;font-weight: bold">[${this.delArr}]</span>, Are you sure?`,
        "notice",
        {
          confirmButtonText: "OK",
          cancelButtonText: " cancel",
          type: "warning",
          center: true,
          dangerouslyUseHTMLString: true,
        }
      )
        .then(() => {
          this.delData();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "The deletion has been cancelled.",
          });
        });
    },
    getList() {
      request.post(this.api.listUrl, this.searchMerge).then((res) => {
        console.log("jjj", res);
        this.tableData = res.data.records;
        this.searchResults = res.data.records;
        this.pageInfo.total = res.data.total;
      });
    },
    saveForm() {
      request.post(this.api.saveUrl, this.formData).then((res) => {
        console.log(res);
        this.$message({
          message: "successful",
          type: "success",
        });
        this.getList();
      });
    },
    delData() {
      request.post(this.api.delUrl, this.delArr).then((res) => {
        console.log(res);
        this.$message({
          message: "successful",
          type: "success",
        });
        this.getList();
      });
    },
  },
};
export default create;
