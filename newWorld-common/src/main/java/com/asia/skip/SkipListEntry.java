package com.asia.skip;

/**
 * @author Xavier.liu
 * @date 2020/5/18 10:51
 */
public class SkipListEntry<V>
{
    public String key;
    public V value;

    public int pos; // 主要为了打印 链表用

    public SkipListEntry<V> up, down, left, right; // 上下左右 四个指针

    public static String negInf = new String("-oo"); // 负无穷
    public static String posInf = new String("+oo"); // 正无穷

    public SkipListEntry(String k, V v)
    {
        key = k;
        value = v;
        up = down = left = right = null;
    }

    public V getValue()
    {
        return value;
    }

    public String getKey()
    {
        return key;
    }

    public V setValue(V val)
    {
        V oldValue = value;
        value = val;
        return oldValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o)
    {
        SkipListEntry<V> entry;
        try
        {
            entry = (SkipListEntry<V>) o; // 检测类型
        } catch (ClassCastException ex)
        {
            return false;
        }
        return (entry.getKey() == key) && (entry.getValue().equals(value));
    }

    @Override
    public String toString()
    {
        return "(" + key + "," + value + ")";
    }
}
