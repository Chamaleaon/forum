const { override, addLessLoader,overrideDevServer } = require("customize-cra");
module.exports = override(
  addLessLoader({
    lessOptions: {
      javascriptEnabled: true,
    },
  })
);
