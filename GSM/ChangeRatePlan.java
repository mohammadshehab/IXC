package com.lhs.wab.action.contract.bscs;

import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.reg.Registry;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractCilAction;
import java.util.HashMap;

public class ChangeRatePlan extends AbstractCilAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/ChangeRatePlan.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/2, 2009/11/06";

  protected void a()
  {
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    return Registry.getInstance().getNode(str);
  }

  protected void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    Long localLong1 = (Long)b.a(Long.class, paramHashMap, "CO_ID");
    String str1 = null;
    if (localLong1 == null)
      str1 = (String)b.a(String.class, paramHashMap, "CO_ID_PUB", true);
    String str2 = (String)b.a(String.class, paramHashMap, "RPCODE_PUB");
    if (str2 == null)
    {
      Long localLong2 = (Long)b.a(Long.class, paramHashMap, "RPCODE", true);
      str2 = a.a(localLong2.longValue(), paramServiceAccessor);
    }
    a.a(localLong1, str1, str2, paramServiceAccessor);
    paramServiceAccessor.commit();
  }

  protected HashMap b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("CO_ID", null);
    localHashMap.put("CO_ID_PUB", null);
    localHashMap.put("RPCODE", null);
    localHashMap.put("RPCODE_PUB", null);
    localHashMap.put("ORDER_DEALER_ID", null);
    return localHashMap;
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.ChangeRatePlan
 * JD-Core Version:    0.6.0
 */