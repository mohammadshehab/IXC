package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractCilAction;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

public class ChangeFriendsAndFamilyAction extends AbstractCilAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/ChangeFriendsAndFamilyAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/f2/0, 2010/06/25";
  protected RegistryNode a;

  public void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    SVLObject localSVLObject1 = K.createSVLObject();
    Long localLong1 = (Long)b.a(Long.class, paramHashMap, "CO_ID", false);
    if (localLong1 != null)
      localSVLObject1.setValue("CO_ID", localLong1);
    else
      localSVLObject1.setValue("CO_ID_PUB", (String)b.a(String.class, paramHashMap, "CO_ID_PUB", true));
    SVLObjectList localSVLObjectList1 = K.createSVLObjectList();
    HashMap localHashMap1 = (HashMap)b.a(HashMap.class, paramHashMap, "FRIENDS_AND_FAMILY", false);
    if (localHashMap1 != null)
    {
      Iterator localIterator = localHashMap1.values().iterator();
      while (localIterator.hasNext())
      {
        HashMap localHashMap2 = (HashMap)localIterator.next();
        SVLObject localSVLObject2 = K.createSVLObject();
        Long localLong2 = (Long)b.a(Long.class, localHashMap2, "IMC_FFCODE");
        if (localLong2 != null)
          localSVLObject2.setValue("IMC_FFCODE", localLong2);
        String str1 = (String)b.a(String.class, localHashMap2, "IMC_FFCODE_PUB", false);
        if (str1 != null)
          localSVLObject2.setValue("IMC_FFCODE_PUB", str1);
        Integer localInteger = (Integer)b.a(Integer.class, localHashMap2, "B_NUMBER_CATEGORY", false);
        if (localInteger != null)
          localSVLObject2.setValue("B_NUMBER_CATEGORY", localInteger);
        SVLObject localSVLObject3 = K.createSVLObject();
        String str2 = (String)b.a(String.class, localHashMap2, "IMC_ORIGIN", false);
        if (str2 != null)
          localSVLObject3.setValue("IMC_ORIGIN", str2);
        Long localLong3 = (Long)b.a(Long.class, localHashMap2, "IMC_ORIGIN_NP", false);
        if (localLong3 != null)
          localSVLObject3.setValue("IMC_ORIGIN_NP", localLong3);
        String str3 = (String)b.a(String.class, localHashMap2, "IMC_DESTINATION", false);
        if (str3 != null)
          localSVLObject3.setValue("IMC_DESTINATION", str3);
        Long localLong4 = (Long)b.a(Long.class, localHashMap2, "IMC_DEST_NP", false);
        if (localLong4 != null)
          localSVLObject3.setValue("IMC_DEST_NP", localLong4);
        Long localLong5 = (Long)b.a(Long.class, localHashMap2, "IMC_SEQ", false);
        if (localLong5 != null)
          localSVLObject3.setValue("IMC_SEQ", localLong5);
        SVLObjectList localSVLObjectList2 = K.createSVLObjectList();
        localSVLObjectList2.add(localSVLObject3);
        localSVLObject2.setValue("Micro cell members", localSVLObjectList2);
        localSVLObjectList1.add(localSVLObject2);
      }
    }
    else
    {
      _log.warning("WAB: Friends and Family part is not set.");
    }
    localSVLObject1.setValue("individual micro cells", localSVLObjectList1);
    _log.finest("WAB: trying to execute MICRO_CELLS.WRITE command.");
    paramServiceAccessor.execute("MICRO_CELLS.WRITE", localSVLObject1);
    _log.finest("WAB: MICRO_CELLS.WRITE command executed.");
    _log.finest("WAB: commit all commands");
    paramServiceAccessor.commit();
  }

  public HashMap b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("CO_ID", null);
    localHashMap.put("CO_ID_PUB", null);
    localHashMap.put("IMC_FFCODE", null);
    localHashMap.put("IMC_FFCODE_PUB", null);
    localHashMap.put("B_NUMBER_CATEGORY", null);
    localHashMap.put("FRIENDS_AND_FAMILY", null);
    localHashMap.put("ORDER_DEALER_ID", null);
    return localHashMap;
  }

  public void end()
  {
    this.a = null;
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.a == null)
      this.a = RegistryHelper.getNode(str);
    return this.a;
  }

  protected void a()
  {
    this.a = c();
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.ChangeFriendsAndFamilyAction
 * JD-Core Version:    0.6.0
 */