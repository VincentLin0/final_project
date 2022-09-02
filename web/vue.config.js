const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  productionSourceMap:false,
  lintOnSave:true
})

/* module.exports = {
  devServer: {
      port: 9875,
      proxy: {
          '/api': {
              target: 'http://localhost:8888',
              changeOrigin: true,
              pathRewrite: {
                  '^/api': ''
              }
          }
      }
  }
}*/
