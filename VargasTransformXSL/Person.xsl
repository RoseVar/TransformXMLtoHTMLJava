<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" />
<xsl:template match="/">
<table border="1">
	<tr>
		<th>nif</th>
		<th>firstname</th>
		<th>surname</th>
		<th>age</th>
		<th>phone</th>
		<th>address</th>
	</tr>
	<xsl:for-each select="persons/person">
	<tr>
		<td><xsl:value-of select="@nif"/></td>
		<td><xsl:value-of select="firstname"/></td>
		<td><xsl:value-of select="surname"/></td>
		<td><xsl:value-of select="age"/></td>	
		<xsl:choose>
			<xsl:when test="count(phone) &gt; 1">
				<td>
					<ul>
						<xsl:for-each select="phone">
							<li> <xsl:value-of select="."/>
							</li>
						</xsl:for-each>	
					</ul>
				</td>
			</xsl:when>
			<xsl:when test="count(phone)= 1">
				<td><xsl:value-of select="phone"/></td>
			</xsl:when>
			<xsl:otherwise>
			 <td>No phone</td>
		</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
			<xsl:when test="count(address) &gt; 1">
				<td>
					<ul>
						<xsl:for-each select="address">
							<li> <xsl:value-of select="concat('(',road/@type, ') ',road,' ', number, ', ', floor, ', ', door, '.')"/>
							</li>
						</xsl:for-each>	
					</ul>
				</td>
			</xsl:when>
			<xsl:when test="count(address)= 1">
				<td><xsl:value-of select="concat('(',address/road/@type, ') ',address/road,' ', address/number, ', ', address/floor, ', ', address/door, '.')"/></td>
			</xsl:when>
			<xsl:otherwise>
			 <td>No address</td>
		</xsl:otherwise>
		</xsl:choose>				
	</tr>
	</xsl:for-each>
</table>
</xsl:template>
</xsl:stylesheet>
