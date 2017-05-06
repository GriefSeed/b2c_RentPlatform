package com.soecode.lyf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.soecode.lyf.entity.Address;
import com.soecode.lyf.entity.Items;
import com.soecode.lyf.entity.Rule;

public interface AddressDao {
	// 查询所有items
	@Select("SELECT * FROM address where account_id=#{accountId}")
	List<Address> queryByAccountId(@Param("accountId") int accountId);

	@Insert("INSERT INTO address VALUES (#{addressId},#{accountId},#{name},#{number},#{addressDetail})")
	int insertAddress(Address address);

	@Update("UPDATE address SET account_id = #{accountId},name = #{name},number = #{number},address_detail = #{addressDetail} WHERE account_id = #{accountId}")
	void modifyAddress(Address address);

	@Delete("DELETE FROM address WHERE addressId = #{addressId}")
	int removeAddress(@Param("addressId") int addressId);
}
