(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["dragdialog"],{"0c3b":function(t,i,e){"use strict";e.r(i);var a=function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("section",{staticClass:"main"},[e("div",{staticClass:"crumbs"},[e("el-breadcrumb",{attrs:{separator:"/"}},[e("el-breadcrumb-item",[e("i",{staticClass:"el-icon-rank"}),t._v(" 拖拽组件")]),e("el-breadcrumb-item",[t._v("拖拽弹框")])],1)],1),e("div",{staticClass:"container"},[e("p",[t._v("通过指令 v-dialogDrag 使 Dialog 对话框具有可拖拽的功能。")]),e("br"),e("el-button",{attrs:{type:"primary"},on:{click:function(i){t.visible=!0}}},[t._v("点我弹框")])],1),e("el-dialog",{directives:[{name:"dialogDrag",rawName:"v-dialogDrag"}],attrs:{title:"拖拽弹框",center:"",visible:t.visible,width:"30%"},on:{"update:visible":function(i){t.visible=i}}},[t._v("\n        我是一个可以拖拽的对话框！\n        "),e("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(i){t.visible=!1}}},[t._v("取 消")]),e("el-button",{attrs:{type:"primary"},on:{click:function(i){t.visible=!1}}},[t._v("确 定")])],1)])],1)},l=[],s={data:function(){return{visible:!1}}},n=s,r=e("4e82"),o=Object(r["a"])(n,a,l,!1,null,null,null);i["default"]=o.exports}}]);