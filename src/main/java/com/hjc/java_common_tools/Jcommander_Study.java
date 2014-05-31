package com.hjc.java_common_tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * 对java应用的输入参数，进行规范解析的示例.
 * 
 * http://jcommander.org/#Types_of_options，简单的说有如下注意点：
 * 
 * <pre>
 * 1、@Parameter(names = {name_0,name_1...name_n}) 这里要特别注意：每个name都必须以中划线"-"开头！
 * 2、boolean类型的量，默认arity=0，也就是比如private boolean debug = false;那么命令行中指定命令-debug就会将debug置为true。如果转换类型失败，会直接报错！
 * 3、默认参数与值之间分隔符是空白字符
 * 4、默认所有参数的required都等于false
 * 5、动态参数，@DynamicParameter(names = "-D", description = "Dynamic parameters go here")  那么argv中如果有：-Da=b -Dc=d   则得到的map为：{k1=a,v1=b;k2=c,v2=d}
 * </pre>
 * 
 */
public class Jcommander_Study {

	@Parameter
	public List<String> parameters = new ArrayList<String>();// 解析后所有剩余的参数将会赋值给它

	@Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
	public Integer verbose = 1;

	@Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
	public String groups;

	@Parameter(names = "-debug", description = "Debug mode")
	public boolean debug = false;

	// 注意：相比于参数debug，这里同样boolean类型的isrun，添加了arity =
	// 1，那么解析的时候，就会把-isrun后面的参数解析为boolean类型的值
	@Parameter(names = "-isrun", description = "Run mode", arity = 1)
	public boolean isrun = true;

	@DynamicParameter(names = "-D", description = "Dynamic parameters go here")
	public Map<String, String> dynamicParams = new HashMap<String, String>();

	@Parameter(names = "-ip", description = "ip address", required = true)
	private String ip;

	// required = false，所以可以不再argv中指定-port。默认所有参数的required都等于false
	@Parameter(names = "-port", description = "port info", required = false)
	private Integer port = 8888;

	@Parameter(names = "-password", description = "请输入连接密码", password = true, echoInput = true)
	private String password;

	@Test
	public void testBasic() {
		Jcommander_Study jct = new Jcommander_Study();
		String[] argv = { "-log", "2", "-groups", "unit1,unit2,unit3",
				"-debug", "-isrun", "false", "-Doption=value", "a", "b", "c",
				"-password", "-ip", "1.2.3.4" };

		JCommander jcommander = new JCommander(jct, argv);

		// 打印usage
		jcommander.usage();

		System.out.println(ToStringBuilder.reflectionToString(jct,
				ToStringStyle.MULTI_LINE_STYLE));

		Assert.assertEquals(2, jct.verbose.intValue());
		Assert.assertEquals("unit1,unit2,unit3", jct.groups);
		Assert.assertEquals(true, jct.debug);
		Assert.assertEquals(false, jct.isrun);
		Assert.assertEquals("1.2.3.4", jct.ip);
		Assert.assertEquals(8888, jct.port.intValue());
		Assert.assertEquals("value", jct.dynamicParams.get("option"));
		Assert.assertEquals(Arrays.asList("a", "b", "c"), jct.parameters);
	}
}
