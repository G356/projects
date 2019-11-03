package com.sample.domain.dto.common;

import com.sample.common.util.CompressUtils;
import lombok.NoArgsConstructor;
import lombok.val;
import org.apache.commons.codec.binary.Base64;
import org.seasar.doma.Domain;

import java.io.Serializable;

@Domain(valueType = byte[].class)
@NoArgsConstructor
public class BZip2Data implements Serializable {

    private static final long serialVersionUID = -4805556024192461766L;

    byte[] data;

    byte[] bzip2;

    // ResultSet.getBytes(int)で取得された値がこのコンストラクタで設定される
    BZip2Data(byte[] bytes) {
        if (data == null) {
            data = CompressUtils.decompress(bytes);
        }
    }

    /**
     * ファクトリメソッド
     *
     * @param bytes
     * @return
     */
    public static BZip2Data of(byte[] bytes) {
        val bZip2Data = new BZip2Data();
        bZip2Data.data = bytes;
        return bZip2Data;
    }

    // PreparedStatement.setBytes(int, bytes)へ設定する値がこのメソッドから取得される
    byte[] getValue() {
        if (bzip2 == null) {
            bzip2 = CompressUtils.compress(data);
        }
        return bzip2;
    }

    public String toBase64() {
        return Base64.encodeBase64String(data);
    }
}
