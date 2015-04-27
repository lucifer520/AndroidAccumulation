package mangues.web.webapp;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;


 
public class TextUtil
{
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if (null==str||str.equals(""))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 分割返回结果
	 * @param response 返回字符串
	 * @return
	 */
	public static Map<String, String> splitResponse(String response)
	{
		
		Map<String, String> map = new HashMap<String, String>();
		if (!TextUtil.isEmpty(response))
		{
			String[] array = response.split("&");
			if (array.length >= 2) 
			{
				String tokenStr = array[0]; //oauth_token=xxxxx
				String secretStr = array[1];//oauth_token_secret=xxxxxxx
				
				String[] token = tokenStr.split("=");
				if (token.length == 2) 
				{
					map.put("oauth_token", token[1]);
				}
				String[] secret = secretStr.split("=");
				if (secret.length == 2) 
				{
					map.put("oauth_token_secret", secret[1]);
				}
				
				
				
			}
		}
		return map;
	}
	
	/**
	 * 参数反编码
	 * 
	 * @param s
	 * @return
	 */
	public static String decode(String s)
	{
		if (s == null)
		{
			return "";
		}
		try
		{
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
