const path = require("path")
module.exports = {
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname, './dist'),
        filename: "bundle.js"
    },
    mode: "development", //"production" or "development"
    module: {
        rules:[
            {
                test: /\.css/,
                use: ['style-loader', 'css-loader']
            }
        ]
    }
}