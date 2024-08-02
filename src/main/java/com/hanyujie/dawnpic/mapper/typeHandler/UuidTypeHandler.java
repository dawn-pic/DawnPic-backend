package com.hanyujie.dawnpic.mapper.typeHandler;

import java.nio.ByteBuffer;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


public class UuidTypeHandler extends BaseTypeHandler<UUID> {

    // Sets a non-null UUID parameter to the PreparedStatement as a byte array
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setBytes(i, uuidToBytes(parameter));
    }

    // Retrieves a nullable UUID result from the ResultSet by column name
    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return bytesToUuid(rs.getBytes(columnName));
    }

    // Retrieves a nullable UUID result from the ResultSet by column index
    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return bytesToUuid(rs.getBytes(columnIndex));
    }

    // Retrieves a nullable UUID result from the CallableStatement by column index
    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return bytesToUuid(cs.getBytes(columnIndex));
    }

    // Converts a UUID to a byte array
    private byte[] uuidToBytes(UUID imageUuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(imageUuid.getMostSignificantBits());
        bb.putLong(imageUuid.getLeastSignificantBits());
        return bb.array();
    }

    // Converts a byte array to a UUID
    private UUID bytesToUuid(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}
