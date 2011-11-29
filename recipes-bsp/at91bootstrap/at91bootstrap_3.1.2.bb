DESCRIPTION = "Initial Bootstrap for AT91SAM9 processors"
HOMEPAGE = "http://www.linux4sam.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM_pn-${PN} = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PR = "r0"

require at91bootstrap.inc

COMPATIBLE_MACHINE	 = "at91sam9260ek|at91sam9xeek|at91sam9g20ek|at91sam9g20ek_2mmc|		XX1"
COMPATIBLE_MACHINE	+= "|at91sam9261ek|at91sam9g10ek|						XX2"
COMPATIBLE_MACHINE	+= "|at91sam9263ek|								XX3"
COMPATIBLE_MACHINE	+= "|at91sam9g45ek|at91sam9g45ekes|at91sam9m10ekes|at91sam9m10g45ek|		XX4"
COMPATIBLE_MACHINE	+= "|at91sam9g15ek|at91sam9g25ek|at91sam9g35ek|at91sam9x25ek|at91sam9x35ek|	XX5"
COMPATIBLE_MACHINE	+= "|at91sam9rlek"

DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_at91sam9260ek = "2"
DEFAULT_PREFERENCE_at91sam9261ek = "2"
DEFAULT_PREFERENCE_at91sam9263ek = "2"
DEFAULT_PREFERENCE_at91sam9g10ek = "2"
DEFAULT_PREFERENCE_at91sam9g20ek = "2"
DEFAULT_PREFERENCE_at91sam9g20ek_2mmc = "2"
DEFAULT_PREFERENCE_at91sam9g45ek = "2"
DEFAULT_PREFERENCE_at91sam9g45ekes = "2"
DEFAULT_PREFERENCE_at91sam9m10ek = "2"
DEFAULT_PREFERENCE_at91sam9m10ekes = "2"
DEFAULT_PREFERENCE_at91sam9g15ek = "2"
DEFAULT_PREFERENCE_at91sam9g25ek = "2"
DEFAULT_PREFERENCE_at91sam9g35ek = "2"
DEFAULT_PREFERENCE_at91sam9x25ek = "2"
DEFAULT_PREFERENCE_at91sam9x35ek = "2"

SRC_URI = "ftp://ftp.emagii.com/pub/at91bootstrap/at91bootstrap-${PV}.tar.bz2 \
	"

S = "${WORKDIR}/at91bootstrap-${PV}"

SRC_URI[md5sum] = "bd7fc689da739aa5163283cf56730888"
SRC_URI[sha256sum] = "d51988d17d85204c42544694fe1a97a125218c1f0e8bf4f8cd2dd1fbf35634f0"

# Atmel base
# SRC_URI[md5sum] = "acfd06daae068ebda508f8c0b97b8255"
# SRC_URI[sha256sum] = "b6829e3199fb6b54fde82f28cc567c7dc298b57e65cd910d9bbf9bac14ac7273"

