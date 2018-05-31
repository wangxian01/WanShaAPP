package com.example.l.wanshaapp.DynamicChoiceness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.l.wanshaapp.DynamicCommentDetails.CommentDetailsActivity;
import com.example.l.wanshaapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cap on 2018/5/7.
 */

public class FragmentChoiceness extends Fragment {

    private List<Map<String,Object>> dataList;
    private ListView mChoicenessListview;
    public String jsonString="{\n" +
            "  \"rows\": [\n" +
            "    {\n" +
            "      \"workObject\": {\n" +
            "        \"UpId\": \"upxgz002\",\n" +
            "        \"UpPortrait\": \"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=845455651,3513714624&fm=27&gp=0.jpg\",\n" +
            "        \"UpName\": \"熬夜重来没猝死\",\n" +
            "        \"UpTime\": \"2018/6\",     \n" +
            "        \"UpText\": \"带领你的部落走向胜利！《部落冲突》是一个战争策略类游戏，在这里：你可以提高自己的野蛮人，弓箭手，野猪骑士，皮卡，龙和其他强大的军队战士与世界各地的玩家对战，提升你的世界排名与其他玩家联合起来建立强大部落与你的队友加入史诗级的部落战争对战可以通过iPad，iPhone以及安卓设备免费下载\",\n" +
            "        \"UpIntroduce\": \"部落冲突开心时刻：炸弹人！兵分两路给我上呀\",\n" +
            "        \"VideoImg\":\"http://coc.kunlun.com/images/top_1.jpg\",\n" +
            "        \"VideoAddress\": \"https://dw-bw6.dwstatic.com/9748b96c8bf0c4518027ec75d6786f8c/5afbe5b5/83/6/1733/8512115-98-1503114648.mp4\",\n" +
            "        \"UpLike\": false,\n" +
            "        \"LikeNumber\": 66,\n" +
            "        \"UpStep\": true,\n" +
            "        \"StepNumber\": 19\n" +
            "     \n" +
            "      }\n" +
            "    },{\n" +
            "      \"workObject\": {\n" +
            "        \"UpId\": \"upxgz002\",\n" +
            "        \"UpPortrait\": \"http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg\",\n" +
            "        \"UpName\": \"游戏小公举\",\n" +
            "        \"UpTime\": \"2018/6\",\n" +
            "        \"UpText\": \"《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！\",\n" +
            "        \"UpIntroduce\": \"剑仙李白潇洒游走，全场收割十步杀一人！\",\n" +
            "        \"VideoImg\":\"http://upload.gezila.com/data/20160912/25941473646354.jpg\",\n" +
            "        \"VideoAddress\": \"http://cs107-hc50.aipai.com/user/282/47030282/1006/card/48083441/card.mp4\",\n" +
            "        \"UpLike\": true,\n" +
            "        \"LikeNumber\": 69,\n" +
            "        \"UpStep\": true,\n" +
            "        \"StepNumber\": 13\n" +
            "      }\n" +
            "    },\n" +
            "   {\n" +
            "      \"workObject\": {\n" +
            "        \"UpId\": \"upfz003\",\n" +
            "        \"UpPortrait\": \"http://p2.so.qhimgs1.com/t012efbbcc60d2887b2.jpg\",\n" +
            "        \"UpName\": \"我不是肥宅\",\n" +
            "        \"UpTime\": \"2018/6\",     \n" +
            "        \"UpText\": \"腾讯光子PUBG联合出品，正版《绝地求生》手游。百人空投，荒岛求生！虚幻4引擎研发，次世代完美画质，重现端游视听感受；8000Mx8000M正版实景地图，打造指尖战场，全方面自由施展战术。\",\n" +
            "        \"UpIntroduce\": \"单人4排20杀吃鸡教学啦！\",\n" +
            "        \"VideoImg\":\"http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg\",\n" +
            "        \"VideoAddress\": \"http://cs245-hc50.aipai.com/user/563/43419563/1006/card/48058984/card.mp4\",\n" +
            "        \"UpLike\": true,\n" +
            "        \"LikeNumber\": 135,\n" +
            "        \"UpStep\": true,\n" +
            "        \"StepNumber\": 164\n" +
            "     \n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_choiceness, null);

        mChoicenessListview = (ListView)view.findViewById(R.id.choiceness_listview);
        initDataList();
        //添加适配器
        AdapterChoiceness adapterChoiceness = new AdapterChoiceness(getActivity(), dataList, R.layout.item_choiceness);
        mChoicenessListview.setAdapter(adapterChoiceness);

        /**
         * 点击listview传递参数（使用map）
         * */
        mChoicenessListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) mChoicenessListview.getItemAtPosition(position);
                final SerializableMap myMap=new SerializableMap();
                myMap.setMap(map);//将map数据添加到封装的myMap<span></span>中
                Bundle bundle = new Bundle();
                bundle.putSerializable("map", myMap);
                Intent intent=new Intent(getActivity(),CommentDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        Gson gson = new Gson();
        BeanChoiceness beanChoiceness = gson.fromJson(jsonString,new TypeToken<BeanChoiceness>() {
        }.getType());
       // Log.e("头像路径：",beanChoiceness.getRows().get(1).getWorkObject().getUpIntroduce());
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < beanChoiceness.getRows().size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            /**
             * UpId : up001
             * UpPortrait : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=845455651,3513714624&fm=27&gp=0.jpg
             * UpName : 腾讯游戏
             * UpTime : 2018/6
             * UpText : 带领你的部落走向胜利！《部落冲突》是一个战争策略类游戏，在这里：你可以提高自己的野蛮人，弓箭手，野猪骑士，皮卡，龙和其他强大的军队战士与世界各地的玩家对战，提升你的世界排名与其他玩家联合起来建立强大部落与你的队友加入史诗级的部落战争对战可以通过iPad，iPhone以及安卓设备免费下载
             * UpIntroduce : 部落冲突开心时刻：炸弹人！兵分两路给我上呀
             * VideoImg : http://coc.kunlun.com/images/top_1.jpg
             * VideoAddress : https://dw-bw6.dwstatic.com/9748b96c8bf0c4518027ec75d6786f8c/5afbe5b5/83/6/1733/8512115-98-1503114648.mp4
             * UpLike : true
             * LikeNumber : 135
             * UpStep : true
             * StepNumber : 164
             */
            map.put("UpId", beanChoiceness.getRows().get(i).getWorkObject().getUpId());
            map.put("UpPortrait", beanChoiceness.getRows().get(i).getWorkObject().getUpPortrait() );
            map.put("ChoicenessUpName",beanChoiceness.getRows().get(i).getWorkObject().getUpName());
            map.put("ChoicenessUpTime", beanChoiceness.getRows().get(i).getWorkObject().getUpTime());
            map.put("ChoicenessUpText", beanChoiceness.getRows().get(i).getWorkObject().getUpText());
            map.put("UpIntroduce", beanChoiceness.getRows().get(i).getWorkObject().getUpIntroduce());
            map.put("VideoImg", beanChoiceness.getRows().get(i).getWorkObject().getVideoImg());
            map.put("ChoicenessViodeoview", beanChoiceness.getRows().get(i).getWorkObject().getVideoAddress());
            map.put("UpLike", beanChoiceness.getRows().get(i).getWorkObject().isUpLike());
            map.put("LikeNumber", beanChoiceness.getRows().get(i).getWorkObject().getLikeNumber());
            map.put("UpStep", beanChoiceness.getRows().get(i).getWorkObject().isUpStep());
            map.put("StepNumber", beanChoiceness.getRows().get(i).getWorkObject().getStepNumber());

            dataList.add(map);
        }

    }

}
