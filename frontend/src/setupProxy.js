const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware('/stores', {
      target: 'http://13.125.242.100:8080',
      changeOrigin: true,
    }),
  );
};