package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.ect.ComponentException;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.action.AbstractContractAction;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertPostpaidContractAction extends AbstractContractAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/ConvertPostpaidContractAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/5, 2010/03/04";
  RegistryNode a;
  private HashMap b;
  private char c;
  private char d;
  private long e;
  private char L;
  private String M;
  private String N;

  protected void a(SVLObject paramSVLObject, HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    _log.finest("WAB: try to get values from given contract");
    Long localLong1 = Long.valueOf(paramSVLObject.getLongValue("CO_ID"));
    String str1 = paramSVLObject.getStringValue("CO_ID_PUB");
    long l = paramSVLObject.getLongValue("RPCODE");
    _log.finest("WAB: values got from given contract");
    String str2 = null;
    String str3 = a.a(l, paramServiceAccessor);
    if (str3 != null)
      str2 = (String)this.b.get(str3);
    if (str2 == null)
    {
      if (_log.isLoggable(Level.WARNING))
        _log.warning("WAB: contract " + str1 + " cannot be converted to postpaid. Rateplan not mapped in registry.");
    }
    else
    {
      SVLObject localSVLObject1 = K.createSVLObject();
      localSVLObject1.setValue("CO_ID", localLong1);
      _log.finest("WAB: trying to execute CONTRACT.READ command.");
      SVLObject localSVLObject2 = paramServiceAccessor.execute("CONTRACT.READ", localSVLObject1);
      _log.finest("WAB: CONTRACT.READ command executed.");
      SVLObjectList localSVLObjectList1 = localSVLObject2.getSVLObjectList("services");
      for (int i = 0; i < localSVLObjectList1.size(); i++)
      {
        SVLObject localSVLObject3 = K.createSVLObject();
        Long localLong2 = Long.valueOf(localSVLObjectList1.get(i).getLongValue("PROFILE_ID"));
        Long localLong3 = Long.valueOf(localSVLObjectList1.get(i).getLongValue("SNCODE"));
        Long localLong4 = Long.valueOf(localSVLObjectList1.get(i).getLongValue("SPCODE"));
        localSVLObject3.setValue("RPCODE", l);
        localSVLObject3.setValue("SNCODE", localLong3);
        localSVLObject3.setValue("SPCODE", localLong4);
        _log.finest("WAB: trying to execute SERVICES.READ command.");
        SVLObject localSVLObject4 = paramServiceAccessor.execute("SERVICES.READ", localSVLObject3);
        _log.finest("WAB: SERVICES.READ command executed.");
        SVLObjectList localSVLObjectList2 = localSVLObject4.getSVLObjectList("NUM_SV");
        for (int j = 0; j < localSVLObjectList2.size(); j++)
        {
          String str4 = String.valueOf(localSVLObjectList2.get(j).getCharValue("SV_TYPE"));
          if (!str4.equals(this.M))
            continue;
          boolean bool = false;
          bool = localSVLObjectList2.get(j).getBooleanValue("MONEY_WALLET");
          if (!bool)
            continue;
          SVLObject localSVLObject5 = K.createSVLObject();
          if (localLong1 != null)
            localSVLObject5.setValue("CO_ID", localLong1);
          else
            localSVLObject5.setValue("CO_ID_PUB", str1);
          localSVLObject5.setValue("SNCODE", localLong3);
          localSVLObject5.setValue("PROFILE_ID", localLong2);
          localSVLObject5.setValue("TASK", this.L);
          localSVLObject5.setValue("PURPOSE", this.c);
          localSVLObject5.setValue("DEBIT_MODE", this.d);
          localSVLObject5.setValue("REASON_ID", this.e);
          if ((this.N != null) && (!"".equals(this.N)))
            localSVLObject5.setValue("TX_TYPE_PUB", this.N);
          _log.finest("WAB: trying to execute BALANCE_ADJUSTMENT_REQUEST.WRITE command.");
          paramServiceAccessor.execute("BALANCE_ADJUSTMENT_REQUEST.WRITE", localSVLObject5);
          _log.finest("WAB: BALANCE_ADJUSTMENT_REQUEST.WRITE command executed.");
        }
      }
      a.a(localLong1.longValue(), str2, paramServiceAccessor);
    }
    a("contract", paramServiceAccessor);
  }

  protected void e()
  {
    _log.finest("WAB: try to read initialization values from the registry");
    this.c = RegistryHelper.getCharEntryValue(this.a, "Purpose", false, 'D');
    this.d = RegistryHelper.getCharEntryValue(this.a, "DebitMode", false, 'D');
    this.e = RegistryHelper.getIntEntryValue(this.a, "ReasonID", false, 6);
    this.L = RegistryHelper.getCharEntryValue(this.a, "Task", false, 'O');
    this.M = RegistryHelper.getStringEntryValue(this.a, "CostControServiceType", false, "B");
    this.N = RegistryHelper.getStringEntryValue(this.a, "TransactionCode", false, "B");
    _log.finest("WAB: try to get rateplan mappings");
    this.b = new HashMap();
    RegistryNode localRegistryNode = f();
    RegistryNode[] arrayOfRegistryNode = localRegistryNode.listNodes();
    for (int i = 0; i < arrayOfRegistryNode.length; i++)
    {
      String str1 = arrayOfRegistryNode[i].getName();
      String str2 = RegistryHelper.getStringEntryValue(arrayOfRegistryNode[i], "PostpaidRateplan", true, null);
      this.b.put(str1, str2);
    }
    _log.finest("WAB: rateplan mappings got");
    _log.finest("WAB: registry read");
  }

  public void end()
  {
    this.E = null;
    this.C = null;
    this.D = null;
    this.l = null;
    this.m = 0L;
    this.n = 0;
    this.o = 0;
    this.p = 0;
  }

  public HashMap d()
  {
    HashMap localHashMap = new HashMap();
    return localHashMap;
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.a == null)
      this.a = RegistryHelper.getNode(str);
    return this.a;
  }

  private RegistryNode f()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Rateplan Mapping Settings";
    RegistryNode localRegistryNode = RegistryHelper.getNode(str);
    if (localRegistryNode == null)
    {
      _log.severe("WAB: Can't read a value from registry. Path:" + str);
      throw new ComponentException("FUNC_FRMWK_SRV.id0301", new Object[] { str });
    }
    return localRegistryNode;
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.ConvertPostpaidContractAction
 * JD-Core Version:    0.6.0
 */