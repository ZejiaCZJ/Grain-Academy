//调用01.js里面的方法
//1 引入01.js文件
const m = require('./01.js')

//2 调用
const a = m.sum(1, 2)
const b = m.subtract(2, 1)

console.log(a)
console.log(b)