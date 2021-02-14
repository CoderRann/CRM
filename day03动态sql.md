### 1.动态sql
* 为什么要动态拼接sql语句?

写SQL语句时，查询条件往往是这样一种非常常见的逻辑：如果客户填了查询信息，则查询该条件；如果客户没填，则返回所有数据。但是当我们有成百上千个查询条件时，不可能每一个查询条件写一条sql语句.

因此在实际开发中，需要将sql语句写成动态的形式。

**核心思想:**有那个查询条件，就动态的在where关键字后挂在那个查询条件

### 2. mybatis的动态sql

#### 2.1 where标签
	当where标签在使用的时候，必须要搭配where标签里的if标签来使用
	同过if标签的判断，如果有至少一个查询条件有效，则展现where关键字
	
	where标签会自动的屏蔽掉第一个连接符 and/or
	<select id = "select" resultType="student">
		select * from tb1_student
	
		<where>
			<if test="name != null" and name != ''">
				and name like '%' #{name} '%'
			</if>
			
			<if test="age=....">
				and ...
			</if>
		</where>
	</select>

#### 2.2 foreach标签

foreach标签中可以接收数组数据类型

* 集合: 后端给前端打回数据用集合
	
* 数组: 前端给后端传数据用数组
	
	select * from table where id in(01,02,03);

	**foreach标签**:用来遍历传递来的数组参数
	
	* collection:标识传递参数的类型
		* arr数组
		* List集合
	* item:每一次遍历出来的元素，在使用改元素时，需要套用在#{}中
	* open:拼接循环的开始符号
	* close:拼接循环的结束符号
	* separator:元素与元素之间的分隔符
		
		
			<select id = "select" resultType="student">
			
				select * from tb1_student
	
				where id in 
	
				<foreach collection="array" item="id" open="(" close=")" separator="," >
					
					#{id} //01,02,03
				</foreach>		
			
			</select>
	
### 3.sql片段的使用
	使用sql标签制作sql片段