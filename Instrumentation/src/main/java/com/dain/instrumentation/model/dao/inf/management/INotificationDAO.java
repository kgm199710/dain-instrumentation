package com.dain.instrumentation.model.dao.inf.management;

import java.util.ArrayList;

import com.dain.instrumentation.model.vo.NotificationVO;

public interface INotificationDAO {

	ArrayList<NotificationVO> selectNotification();

}
