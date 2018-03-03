package sysu.zyb.panellisttest;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sysu.zyb.panellistlibrary.PanelListLayout;


public class MainActivity extends AppCompatActivity {

    private PanelListLayout pl_root;
    private ListView lv_content;

    private MyPanelListAdapter adapter;

    private List<Map<String, String>> contentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initContentDataList();

        adapter = new MyPanelListAdapter(this, pl_root, lv_content, R.layout.item_content, contentList);
        adapter.setInitPosition(0);
        adapter.setSwipeRefreshEnabled(true);
        adapter.setRowDataList(getRowDataList());
        adapter.setTitle("动作列表");
        adapter.setRowDivider(getResources().getDrawable(R.drawable.row_item_divider));
        //adapter.setColumnDivider(getResources().getDrawable(R.drawable.row_item_divider));
        adapter.setOnRefreshListener(new CustomRefreshListener());
        pl_root.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_menu_updateData:
                changeContentDataList();
                break;
            case R.id.id_menu_insert:
                insertData();
                break;
            case R.id.id_menu_delete:
                removeData();
                break;
            case R.id.id_menu_next:
                Intent intent = new Intent(MainActivity.this, RoomActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        ((ArrayAdapter) (adapter.getContentListView().getAdapter())).notifyDataSetChanged();
        return true;
    }

    private void initView() {

        pl_root = (PanelListLayout) findViewById(R.id.id_pl_root);
        lv_content = (ListView) findViewById(R.id.id_lv_content);

        //设置listView为多选模式，长按自动触发
        lv_content.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv_content.setMultiChoiceModeListener(new MultiChoiceModeCallback());

        //listView的点击监听
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "你选中的position为：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomRefreshListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {
            // do sth here, example:
            Toast.makeText(MainActivity.this, "custom SwipeRefresh listener", Toast.LENGTH_SHORT).show();
            adapter.getSwipeRefreshLayout().setRefreshing(false);// don`t forget to call this
        }
    }

    /** 生成一份横向表头的内容
     *
     * @return List<String>
     */
    private List<String> getRowDataList(){
        List<String> rowDataList = new ArrayList<>();
        rowDataList.add("第1列");
        rowDataList.add("第2列");
        rowDataList.add("第3列");
        rowDataList.add("第4列");
        rowDataList.add("第5列");
        rowDataList.add("第6列");
        rowDataList.add("第7列");
        rowDataList.add("第8列");
        rowDataList.add("第9列");
        rowDataList.add("第10列");
        rowDataList.add("第11列");
        rowDataList.add("第12列");
        rowDataList.add("第13列");
        rowDataList.add("第14列");
        rowDataList.add("第15列");
        rowDataList.add("第16列");

        return rowDataList;
    }

    /**
     * 初始化content数据
     */
    private void initContentDataList() {
        for (int i = 1; i < 22; i++) {
            Map<String, String> data = new HashMap<>();
            data.put("1", "第" + i + "行第1个");
            data.put("2", "第" + i + "行第2个");
            data.put("3", "第" + i + "行第3个");
            data.put("4", "第" + i + "行第4个");
            data.put("5", "第" + i + "行第5个");
            data.put("6", "第" + i + "行第6个");
            data.put("7", "第" + i + "行第7个");
            data.put("8", "第" + i + "行第8个");
            data.put("9", "第" + i + "行第9个");
            data.put("10", "第" + i + "行第10个");
            data.put("11", "第" + i + "行第11个");
            data.put("12", "第" + i + "行第12个");
            data.put("13", "第" + i + "行第13个");
            data.put("14", "第" + i + "行第14个");
            data.put("15", "第" + i + "行第15个");
            data.put("16", "第" + i + "行第16个");

            contentList.add(data);
        }
    }

    /**
     * 更新content数据
     */
    private void changeContentDataList() {
        contentList.clear();
        for (int i = 1; i < 22; i++) {
            Map<String, String> data = new HashMap<>();
            data.put("1", "第" + i + "第1个");
            data.put("2", "第" + i + "第2个");
            data.put("3", "第" + i + "第3个");
            data.put("4", "第" + i + "第4个");
            data.put("5", "第" + i + "第5个");
            data.put("6", "第" + i + "第6个");
            data.put("7", "第" + i + "第7个");
            data.put("8", "第" + i + "第8个");
            data.put("9", "第" + i + "第9个");
            data.put("10", "第" + i + "第10个");
            data.put("11", "第" + i + "第11个");
            data.put("12", "第" + i + "第12个");
            data.put("13", "第" + i + "第13个");
            data.put("14", "第" + i + "第14个");
            data.put("15", "第" + i + "第15个");
            data.put("16", "第" + i + "第16个");
            contentList.add(data);
        }
    }

    /**
     * 插入一个数据
     */
    private void insertData(){
        Map<String, String> data = new HashMap<>();
        data.put("1", "插入1");
        data.put("2", "插入2");
        data.put("3", "插入3");
        data.put("4", "插入4");
        data.put("5", "插入5");
        data.put("6", "插入6");
        data.put("7", "插入7");
        data.put("8", "插入8");
        data.put("9", "插入9");
        data.put("10", "插入10");
        data.put("11", "插入11");
        data.put("12", "插入12");
        data.put("13", "插入13");
        data.put("14", "插入14");
        data.put("15", "插入15");
        data.put("16", "插入16");
        contentList.add(18,data);
    }

    /**
     * 删除一个数据
     */
    private void removeData(){
        contentList.remove(10);
    }

    /**
     * 多选模式的监听器
     */
    private class MultiChoiceModeCallback implements ListView.MultiChoiceModeListener {

        private View actionBarView;
        private TextView tv_selectedCount;

        /**
         * 进入ActionMode时调用
         * 可设置一些菜单
         *
         * @param mode
         * @param menu
         * @return
         */
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // menu
            getMenuInflater().inflate(R.menu.menu_multichoice, menu);
            // actionBar
            if (actionBarView == null) {
                actionBarView = LayoutInflater.from(MainActivity.this).inflate(R.layout.actionbar_listviewmultichoice, null);
                tv_selectedCount = (TextView) actionBarView.findViewById(R.id.id_tv_selectedCount);
            }
            mode.setCustomView(actionBarView);
            return true;
        }

        /**
         * 和onCreateActionMode差不多的时机调用，不写逻辑
         *
         * @param mode
         * @param menu
         * @return
         */
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        /**
         * 当ActionMode的菜单项被点击时
         *
         * @param mode
         * @param item
         * @return
         */
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.id_menu_selectAll:
                    for (int i = 0; i < lv_content.getAdapter().getCount(); i++) {
                        lv_content.setItemChecked(i, true);
                    }
                    tv_selectedCount.setText(String.valueOf(lv_content.getAdapter().getCount()));
                    break;
                case R.id.id_menu_draw:
                    //draw
                    SparseBooleanArray booleanArray = lv_content.getCheckedItemPositions();
                    Log.d("ybz", booleanArray.toString());

                    List<Integer> checkedItemPositionList = new ArrayList<>();
                    for (int i = 0; i < contentList.size(); i++) {
                        if (lv_content.isItemChecked(i)) {
                            checkedItemPositionList.add(i);
                            Log.d("ybz", "被选中的item： " + i);
                        }
                    }

                    StringBuilder checkedItemString = new StringBuilder();
                    for (int i = 0; i < checkedItemPositionList.size(); i++) {
                        checkedItemString.append(checkedItemPositionList.get(i) + ",");
                    }

                    Toast.makeText(MainActivity.this, "你选中的position有：" + checkedItemString, Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

        /**
         * 退出ActionMode时调用
         *
         * @param mode
         */
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            lv_content.clearChoices();
        }

        /**
         * 当item的选中状态发生改变时调用
         *
         * @param mode
         * @param position
         * @param id
         * @param checked
         */
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            int selectedCount = lv_content.getCheckedItemCount();
            tv_selectedCount.setText(String.valueOf(selectedCount));
            ((ArrayAdapter) lv_content.getAdapter()).notifyDataSetChanged();
        }
    }
}
