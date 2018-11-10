#Android流式布局

####功能

 Android流式布局，支持单选、多选等，适合用于产品标签等。

####原项目地址

1. https://github.com/hongyangAndroid/FlowLayout

####扩展功能

1.  原功能：类似CheckBox实现单选或多选；checkbox的特点是我可以选中或者取消选中；
2.  扩展功能：增加 只能选中，不能取消选中，类似RadioButton的效果；
3.  代码解析: 原 attrs.xml 文件中，增加选择模式 checkbox or radio 效果，默认checkbox
  
      <attr name="select_mode">
          <enum name="checkbox" value="-1" />
          <enum name="radio" value="0" />
      </attr>
      
4. 扩展功能单选使用方式：直接声明 选择模式即可
    布局中：
    <com.zhy.view.flowlayout.TagFlowLayout
        android:id="@+id/tag_flow_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/tv_cycle_name"
        android:layout_gravity="top"
        android:layout_marginLeft="@dimen/dp15"
        android:layout_marginRight="@dimen/dp15"
        zhy:max_select="1"
        zhy:select_mode="radio" />
    
     
    adapter需要手动去声明选中和未选中的样式（类似参考下边）
      adapter = new TagAdapter<BidiEntity>(list) {
                @Override
                public void onSelected(int position, View view) {
                    super.onSelected(position, view);
                    ((TextView) view).setBackground(context.getResources().getDrawable(R.drawable.shape_solid_red_10));
                    ((TextView) view).setTextColor(context.getResources().getColor(R.color.colorWhite));
                }
    
                @Override
                public void unSelected(int position, View view) {
                    super.unSelected(position, view);
                    ((TextView) view).setBackground(context.getResources().getDrawable(R.drawable.shape_solid_f6f5f5_10));
                    ((TextView) view).setTextColor(context.getResources().getColor(R.color.color3));
                }
            };
  
