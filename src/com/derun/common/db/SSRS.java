package com.derun.common.db;

import org.apache.log4j.*;

import com.derun.common.util.StrTool;
import com.derun.common.util.SysConst;

import java.util.Vector;



public class SSRS
{
	private static Logger log = Logger.getLogger(com.derun.common.db.SSRS.class.getName());
	
    private Vector RData = new Vector();
    public int MaxCol = 0;
    public int MaxRow = 0;
    public int MaxNumber = 0;

    //false没有错，true有错
    public boolean ErrorFlag = false;
    public SSRS()
    {
    }

    public SSRS(int n)
    {
        MaxCol = n;
    }


    public void SetText(String strValue)
    {
        RData.addElement(strValue);
        MaxNumber = RData.size();
        if ((MaxNumber % MaxCol) == 0)
        {
            MaxRow = MaxNumber / MaxCol;
        }
        else
        {
            MaxRow = MaxNumber / MaxCol + 1;
        }

    }

    public String GetText(int cRow, int cCol)
    {
        String Result = "";
        int Number = (cRow - 1) * MaxCol + cCol - 1;
        if (Number <= MaxNumber)
        {
            Result = (String) RData.get(Number);
        }
        else
        {
            // @@错误处理
            log.error("指定的位置在结果集中没有数据");
            this.ErrorFlag = true;
        }
        return Result;
    }

    public int getMaxCol()
    {
        return MaxCol;
    }

    public int getMaxRow()
    {
        return MaxRow;
    }

    public int getMaxNumber()
    {
        return MaxNumber;
    }

    public void setMaxCol(int aMaxCol)
    {
        MaxCol = aMaxCol;
    }

    public void setMaxRow(int aMaxRow)
    {
        MaxRow = aMaxRow;
    }

    public void setMaxNumber(int aMaxNumber)
    {
        MaxNumber = aMaxNumber;
    }

    public void Clear()
    {
        RData.clear();
        MaxRow = 0;
        MaxCol = 0;
        MaxNumber = 0;
    }

    public String[] getRowData(int cRow)
    {
        String StrArr[] = new String[MaxCol];
        for (int i = 0; i < MaxCol; i++)
        {
            StrArr[i] = this.GetText(cRow, i + 1);
        }
        return StrArr;
    }

    public String[][] getAllData()
    {
        String StrArr[][] = new String[MaxRow][MaxCol];
        for (int j = 0; j < MaxRow; j++)
        {
            StrArr[j] = this.getRowData(j + 1);
        }
        return StrArr;
    }

    public String encode()
    {
        String strReturn = "";
        if (MaxNumber != 0)
        {
            strReturn = "0" + SysConst.PACKAGESPILTER + String.valueOf(MaxRow) +
                        SysConst.RECORDSPLITER;
            for (int i = 1; i <= MaxRow; i++)
            {
                for (int j = 1; j <= MaxCol; j++)
                {
                    if (j != MaxCol)
                    {
                        strReturn = strReturn + GetText(i, j) +
                                    SysConst.PACKAGESPILTER;
                    }
                    else
                    {
                        strReturn += GetText(i, j);
                    }
                }
                if (i != MaxRow)
                {
                    strReturn += SysConst.RECORDSPLITER;
                }
            }
        }
        return strReturn;
    }

    public boolean addCol(SSRS aSSRS)
    {
        if (!(this.MaxRow == aSSRS.getMaxRow()))
        {
            // @@错误处理
            log.error("合并的两个结果集行数不等！！！");
            return false;
        }
        else
        {
            SSRS bSSRS = null;
            try
            {
                bSSRS = (SSRS)this.clone();
            }
            catch (Exception ex)
            {
                // @@错误处理
                ex.printStackTrace();
                log.error("克隆出错！！！");
                return false;
            }
            this.Clear();
            this.setMaxCol(bSSRS.getMaxCol() + aSSRS.getMaxCol());
//    this.setMaxRow(bSSRS.getMaxRow());
//    this.setMaxNumber(bSSRS.getMaxNumber()+aSSRS.getMaxNumber());
            for (int row = 1; row <= aSSRS.getMaxRow(); row++)
            {
                for (int bcol = 1; bcol <= bSSRS.getMaxCol(); bcol++)
                {
                    this.SetText(StrTool.cTrim(bSSRS.GetText(row, bcol)));
                }
                for (int acol = 1; acol <= aSSRS.getMaxCol(); acol++)
                {
                    this.SetText(StrTool.cTrim(aSSRS.GetText(row, acol)));
                }
            }

            return true;
        }
    }

    public boolean addRow(SSRS aSSRS)
    {
        if (!(this.MaxCol == aSSRS.getMaxCol()))
        {
            // @@错误处理
            log.debug("合并的两个结果集列数不等！！！");
            return false;
        }
        else
        {
            SSRS bSSRS = null;
            try
            {
                bSSRS = (SSRS)this.clone();
            }
            catch (Exception ex)
            {
                // @@错误处理
                ex.printStackTrace();
                log.debug("克隆出错！！！");
                return false;
            }
            this.Clear();
            this.setMaxCol(bSSRS.getMaxCol());
            for (int row = 1; row <= bSSRS.getMaxRow(); row++)
            {
                for (int col = 1; col <= bSSRS.getMaxCol(); col++)
                {
                    this.SetText(StrTool.cTrim(bSSRS.GetText(row, col)));
                }
            }
            for (int row = 1; row <= aSSRS.getMaxRow(); row++)
            {
                for (int col = 1; col <= aSSRS.getMaxCol(); col++)
                {
                    this.SetText(StrTool.cTrim(aSSRS.GetText(row, col)));
                }
            }
            return true;
        }
    }

    public Object clone() throws CloneNotSupportedException
    {
        Object tObj = null;
        SSRS tSSRS = new SSRS();
        tSSRS.setMaxCol(this.getMaxCol());
        tSSRS.setMaxNumber(this.getMaxNumber());
        tSSRS.setMaxRow(this.getMaxRow());
        tSSRS.ErrorFlag = this.ErrorFlag;
        tSSRS.RData = (Vector)this.RData.clone();
        tObj = tSSRS;
        return tObj;
    }

}
