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
	
使用sql标签制作sql片段.<sql id=" "> 
	
* 作用:用来代替sql语句中的某些代码	
如果你的mapper映射文件中的某些代码出现了大量的重复，我们可以使用sql片段来代替他们
* 举例
	
		<sql id="sql1">
			select * from tb1_stu;
		</sql>
		
		<select id="select" resultType="Student">
			<include refid="sql1"/> where id=#{id}
		</select>

### 4.多表联查
多表联查案例
关联学生表和班级表

* 1)查询出学生姓名和班级名称
	
	**注意**set无序不重复集合

		List<Map<String, Object>> mapList = studentDao.selectDuo();
        for (Map<String, Object> map:
             mapList) {
            Set<String> set = map.keySet();

            for (String key:set
                 ) {
                System.out.println("key: "+key);
                System.out.println("value: "+map.get(key));
            }
            System.out.println("------------------------------------");
        }

  		<select id ="selectDuo" resultType="map">
        	select
       	 	s.name as sname,
        	c.name as cname
        	from tbl_stu s
        	join tb1_classroom c
        	on s.classroomid = c.id
    	</select>

* 2)查询出学生和班级所有信息，使用VO(Value object)类
	
	如果需要为前端展现的数据，使用一个domain类型不足以表现出来这些数据，我们可以考虑
	* map
	* VO
	
	vo指的是创建出来的一个类，这个类的属性是完全由我们自己定义,且不需要像domain一样完全取相同的名字

