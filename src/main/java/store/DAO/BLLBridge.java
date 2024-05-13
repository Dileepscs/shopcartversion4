package store.DAO;

import store.BLL.Bll;
import store.BLL.CalculateBLL;
import store.BLL.Common;
import store.BLL.CommonBLL;

public class BLLBridge {
	public static Bll getBLLObject() {
		return new CalculateBLL();
	}
	public static CommonBLL getCommonBLLObject() {
		return new Common();
	}
}
