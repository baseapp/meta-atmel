#!/bin/sh

rm	-f	at91*.conf
mkboard ()
{

	BOARD=`echo $1	| tr '[:lower:]' '[:upper:]'`
	board=`echo $1	| tr '[:upper:]' '[:lower:]'`
	chip=`echo $board	| sed "s/ek//g"	| sed "s/es//g"`
	echo "chip=$chip"
	cp	template.conf	$1.conf
	sed	-i	"s/AT91SAM9G35EK/$BOARD/g"	$1.conf
	sed	-i	"s/at91sam9g35ek/$board/g"	$1.conf
	sed	-i	"s/at91sam9g35/$chip/g"	$1.conf	
}

mkboard	at91sam9260ek

if	[ "A" == "A" ] ; then
mkboard	at91sam9261ek
mkboard	at91sam9263ek
mkboard	at91sam9g10ek
mkboard	at91sam9g15ek
mkboard	at91sam9g20ek
mkboard	at91sam9g20ek_2mmc
mkboard	at91sam9g25ek
mkboard	at91sam9g45ek
mkboard	at91sam9g45ekes
mkboard	at91sam9g46ek
mkboard	at91sam9m10ek
mkboard	at91sam9m10ekes
mkboard	at91sam9m10g45ek
mkboard	at91sam9m11ek
mkboard	at91sam9rlek
mkboard	at91sam9xeek
mkboard	at91sam9x25ek
mkboard	at91sam9x35ek
fi

