package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.ect.ComponentException;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractContractAction;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertPrepaidContractAction extends AbstractContractAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/ConvertPrepaidContractAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/f1/2, 2010/03/16";
  private static final int a = 0;
  private static final int b = 2;
  private static final char c = 'R';
  private RegistryNode d;
  private HashMap e;
  private String L;
  private long M;
  private int N;
  private char O;
  private long P;
  private int Q;
  private int R;
  private long S = 0L;

  protected void a(SVLObject paramSVLObject, HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    Long localLong = (Long)b.a(Long.class, paramHashMap, "WORKFLOW_ENTITY_INTERNAL_KEY", true);
    _log.finest("WAB: try to get values from given contract");
    long l1 = paramSVLObject.getLongValue("CO_ID");
    String str1 = paramSVLObject.getStringValue("CO_ID_PUB");
    long l2 = paramSVLObject.getLongValue("RPCODE");
    _log.finest("WAB: values got from given contract");
    String str2 = null;
    String str3 = a.a(l2, paramServiceAccessor);
    if (str3 != null)
      str2 = (String)this.e.get(str3);
    if (str2 == null)
    {
      if (_log.isLoggable(Level.WARNING))
        _log.warning("WAB: contract " + str1 + " cannot be converted to prepaid. Rateplan not mapped in registry.");
    }
    else
      try
      {
        a.a(l1, str2, paramServiceAccessor);
        a("contract", paramServiceAccessor);
        a(f() + a(Long.valueOf(l1), paramServiceAccessor, localLong).longValue());
        a(paramServiceAccessor, l1, localLong.longValue());
        _log.finest("WAB: try to add money wallet service");
        com.lhs.wab.action.service.bscs.a.a(l1, -1L, str2, this.N, this.L, this.M, this.P, paramServiceAccessor);
        _log.finest("WAB: money wallet service added");
        a("contract", paramServiceAccessor);
        a(f() + a(Long.valueOf(l1), paramServiceAccessor, localLong).longValue());
        a(paramServiceAccessor, l1, localLong.longValue());
        _log.finest("WAB: try to execute CONTRACT.WRITE");
        SVLObject localSVLObject = K.createSVLObject();
        localSVLObject.setValue("CO_ID", l1);
        localSVLObject.setValue("CO_NONEXPL_SERV_PAYMENT_TYPE", this.O);
        paramServiceAccessor.execute("CONTRACT.WRITE", localSVLObject);
        _log.finest("WAB: CONTRACT.WRITE executed");
        a("contract", paramServiceAccessor);
        a(f() + a(Long.valueOf(l1), paramServiceAccessor, localLong).longValue());
      }
      catch (ComponentException localComponentException)
      {
        if (_log.isLoggable(Level.WARNING))
          _log.warning("WAB: contract " + str1 + " cannot be converted to prepaid. " + localComponentException.getErrorCode());
      }
  }

  private Long a(Long paramLong1, ServiceAccessor paramServiceAccessor, Long paramLong2)
  {
    SVLObject localSVLObject1 = K.createSVLObject();
    SVLObject localSVLObject2 = null;
    SVLObjectList localSVLObjectList = null;
    Long localLong = Long.valueOf(0L);
    localSVLObject1.setValue("RE_SEARCH_MASK", Integer.valueOf(1));
    localSVLObject1.setValue("CO_ID", paramLong1);
    localSVLObject2 = paramServiceAccessor.execute("REQUESTS.SEARCH", localSVLObject1);
    localSVLObjectList = localSVLObject2.getSVLObjectList("requests");
    localLong = Long.valueOf(localLong.longValue() + localSVLObjectList.size());
    if (localSVLObjectList.size() == 0)
    {
      localSVLObject1.setValue("PROCESS", this.I + "_" + String.valueOf(paramLong2));
      localSVLObject2 = paramServiceAccessor.execute("CONCURRENT_REQUEST.SEARCH", localSVLObject1);
      localSVLObjectList = localSVLObject2.getSVLObjectList("REQUESTS");
      if (localSVLObjectList != null)
        localLong = Long.valueOf(localLong.longValue() + localSVLObjectList.size());
    }
    return localLong;
  }

  protected Long a(Set paramSet, ServiceAccessor paramServiceAccessor, String paramString)
  {
    Long localLong = Long.valueOf(f());
    a(0L);
    return localLong;
  }

  protected void a(long paramLong)
  {
    this.S = paramLong;
  }

  protected long f()
  {
    return this.S;
  }

  private void a(ServiceAccessor paramServiceAccessor, long paramLong1, long paramLong2)
  {
    int i = this.Q < 1 ? 1 : this.Q;
    int j = this.R < 1 ? 1 : this.R;
    int k = 0;
    _log.finest("WAB: Waiting pending requests for contract id " + paramLong1);
    while (true)
    {
      k++;
      if (k > j)
        break;
      _log.finest("WAB: Attempt " + k + " of " + j);
      if (!b(paramServiceAccessor, paramLong1, paramLong2))
      {
        _log.finest("WAB: Not waiting anymore for pending requests");
      }
      else
      {
        if (k == j)
        {
          _log.finest("WAB: Last attempt was reached");
          continue;
        }
        _log.finest("WAB: Waiting " + i + " seconds to retry...");
        try
        {
          Thread.sleep(i * 1000L);
        }
        catch (InterruptedException localInterruptedException)
        {
          _log.severe("WAB: Interrupted Exception occurred during " + getClass().getName() + " sleep time");
          throw new ComponentException("WAB.InterruptedException", new String[] { getClass().getName() }, localInterruptedException);
        }
      }
    }
  }

  private boolean b(ServiceAccessor paramServiceAccessor, long paramLong1, long paramLong2)
  {
    SVLObject localSVLObject1 = K.createSVLObject();
    localSVLObject1.setValue("CO_ID", paramLong1);
    localSVLObject1.setValue("RE_SEARCH_MASK", 1);
    _log.finest("WAB: Executing REQUESTS.SEARCH");
    SVLObject localSVLObject2 = paramServiceAccessor.execute("REQUESTS.SEARCH", localSVLObject1);
    SVLObjectList localSVLObjectList = localSVLObject2.getSVLObjectList("requests");
    _log.finest("WAB: Contract id <" + paramLong1 + "> has <" + localSVLObjectList.size() + "> pending requests");
    _log.finest("WAB: REQUESTS.SEARCH executed");
    if (localSVLObjectList.isEmpty())
    {
      localSVLObject1.setValue("PROCESS", this.I + "_" + String.valueOf(paramLong2));
      localSVLObject2 = paramServiceAccessor.execute("CONCURRENT_REQUEST.SEARCH", localSVLObject1);
      localSVLObjectList = localSVLObject2.getSVLObjectList("REQUESTS");
      return !localSVLObjectList.isEmpty();
    }
    return !localSVLObjectList.isEmpty();
  }

  protected void e()
  {
    RegistryNode localRegistryNode1 = c();
    _log.finest("WAB: try to read initialization values from the registry");
    this.L = RegistryHelper.getStringEntryValue(localRegistryNode1, "MoneyWalletServicePubCode", true, null);
    this.M = RegistryHelper.getIntEntryValue(localRegistryNode1, "ProfileCode", false, 0);
    this.N = RegistryHelper.getIntEntryValue(localRegistryNode1, "CosPendingStatus", false, 2);
    this.P = RegistryHelper.getIntEntryValue(localRegistryNode1, "UserReasonKey", true, -1);
    this.O = RegistryHelper.getCharEntryValue(localRegistryNode1, "CoNonExplServPaymentType", false, 'R');
    this.Q = RegistryHelper.getIntEntryValue(localRegistryNode1, "WaitTime", true, 0);
    this.R = RegistryHelper.getIntEntryValue(localRegistryNode1, "MaxRetries", true, 0);
    _log.finest("WAB: try to get rateplan mappings");
    this.e = new HashMap();
    RegistryNode localRegistryNode2 = g();
    RegistryNode[] arrayOfRegistryNode = localRegistryNode2.listNodes();
    for (int i = 0; i < arrayOfRegistryNode.length; i++)
    {
      String str1 = arrayOfRegistryNode[i].getName();
      String str2 = RegistryHelper.getStringEntryValue(arrayOfRegistryNode[i], "PrepaidRateplan", true, null);
      this.e.put(str1, str2);
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
    this.e = null;
    this.L = null;
    this.M = 0L;
    this.N = 0;
    this.O = '\000';
    this.P = 0L;
  }

  public HashMap d()
  {
    HashMap localHashMap = new HashMap();
    return localHashMap;
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.d == null)
      this.d = RegistryHelper.getNode(str);
    return this.d;
  }

  private RegistryNode g()
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
 * Qualified Name:     com.lhs.wab.action.contract.bscs.ConvertPrepaidContractAction
 * JD-Core Version:    0.6.0
 */