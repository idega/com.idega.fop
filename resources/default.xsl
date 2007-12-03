<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
	<xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes" />
	<xsl:template match="propertyRoot">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<!-- page templates goes here -->
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4">
				<!-- page content goes here -->
				<fo:flow flow-name="xsl-region-body">
					<xsl:apply-templates select="propertyTree" />
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template match="propertyTree">
		<fo:list-block font-size="11pt" line-height="12pt" font-family="sans-serif">
			<fo:list-item>
				<fo:list-item-label end-indent="label-end()">
					<fo:block font-family="sans-serif"></fo:block>
				</fo:list-item-label>
				<fo:list-item-body start-indent="body-start()" end-indent="body-start()">
					<fo:block space-after="12pt" space-before="12pt">
						<xsl:choose>
							<xsl:when test="description != ''">
								<fo:block border-bottom-width="0.1pt" border-bottom-style="solid" space-after="6pt" font-weight="bold" font-size="13pt" line-height="18pt" font-family="sans-serif">
									<xsl:value-of select="description" />
								</fo:block>
							</xsl:when>
						</xsl:choose>
						<xsl:apply-templates select="value" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
		</fo:list-block>
	</xsl:template>

	<xsl:template match="property">
		<fo:block font-size="10pt" font-family="sans-serif" line-height="12pt">
			<!-- bullet plus 1em space -->
			&#x2022; &#x2001;
			<xsl:choose>
				<xsl:when test="description =''">
					<!-- description empty? -->
				</xsl:when>
				<xsl:otherwise>
					<!-- description not empty add colon plus 1em space -->
					<fo:inline font-weight="bold" vertical-align="top">
						<xsl:value-of select="description" />
						:&#x2001;
					</fo:inline>
				</xsl:otherwise>
			</xsl:choose>
			<fo:inline font-weight="normal">
				<xsl:value-of select="value" />
			</fo:inline>
		</fo:block>
	</xsl:template>

	<xsl:template match="propertyUnit">
		<fo:block font-size="10pt" font-family="sans-serif" line-height="12pt">
			<!-- bullet plus 1em space -->
			&#x2022; &#x2001;
			<xsl:choose>
				<xsl:when test="description =''">
					<!-- description empty? -->
				</xsl:when>
				<xsl:otherwise>
					<!-- description not empty add colon plus 1em space -->
					<fo:inline font-weight="bold" vertical-align="top">
						<xsl:value-of select="description" />
						: &#x2001;
					</fo:inline>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:value-of select="value" />
			&#x2001;
			<xsl:choose>
				<!-- display Ohm as greek letter -->
				<xsl:when test="unit ='Ω'">
					<fo:inline font-family="Symbol">&#x2126;</fo:inline>
				</xsl:when>
				<xsl:when test="unit ='MΩ'">
					M
					<fo:inline font-family="Symbol">&#x2126;</fo:inline>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="unit" />
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>
	</xsl:template>

	<xsl:template match="propertyValueDescription">
		<fo:block font-size="10pt" font-family="sans-serif" line-height="12pt">
			<!-- bullet plus 1em space -->
			&#x2022; &#x2001;
			<xsl:choose>
				<xsl:when test="description =''">
					<!-- description empty? -->
				</xsl:when>
				<xsl:otherwise>
					<!-- description not empty add colon plus 1em space -->
					<fo:inline font-weight="bold" vertical-align="top">
						<xsl:value-of select="description" />
						: &#x2001;
					</fo:inline>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:value-of select="valueDescription" />
		</fo:block>
	</xsl:template>

	<xsl:template match="propertyThreeValuesUnit">
		<fo:block font-size="10pt" font-family="sans-serif" line-height="12pt">
			&#x2022; &#x2001;
			<fo:inline font-weight="bold" vertical-align="top">
				<xsl:value-of select="description" />
				: &#x2001;
			</fo:inline>
			<xsl:value-of select="value" />
			&#x2001;
			<xsl:value-of select="description2" />
			&#x2001;
			<xsl:value-of select="value2" />
			&#x2001;
			<xsl:value-of select="description3" />
			&#x2001;
			<xsl:value-of select="value3" />
			&#x2001;
			<xsl:value-of select="unit" />
		</fo:block>
	</xsl:template>

	<xsl:template match="member">
		<fo:table-row>
			<xsl:if test="function = 'lead'">
				<xsl:attribute name="font-weight">bold</xsl:attribute>
			</xsl:if>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="name" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="function" />
				</fo:block>
			</fo:table-cell>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="email" />
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>
	<!-- added by thomas -->
</xsl:stylesheet>