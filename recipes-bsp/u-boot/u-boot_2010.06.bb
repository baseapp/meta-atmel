DESCRIPTION = "U-boot for the AT91 Series" 
HOMEPAGE = "www.linux4sam.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM_pn-${PN} = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PR = "r0"

PV = "2010.06"

require u-boot.inc

# To build u-boot for your machine, provide the following lines in your machine
# config, replacing the assignments as appropriate for your machine.
# UBOOT_MACHINES = "at91sam9g35ek at91sam9g35ek_dataflash at91sam9g35eknandflash"
#?UBOOT_ENTRYPOINT = "0x80008000"
#?UBOOT_LOADADDRESS = "0x80008000"

COMPATIBLE_MACHINE	 = "at91sam9260ek|at91sam9xeek|at91sam9g20ek|at91sam9g20ek_2mmc|			XX1"
COMPATIBLE_MACHINE	+= "|at91sam9261ek|at91sam9g10ek|							XX2"
COMPATIBLE_MACHINE	+= "|at91sam9263ek|									XX3"
COMPATIBLE_MACHINE	+= "|at91sam9g45ek|at91sam9g45ekes|at91sam9m10ekes|at91sam9m10g45ek|			XX4"
COMPATIBLE_MACHINE	+= "|at91sam9g15ek|at91sam9g25ek|at91sam9g35ek|at91sam9x25ek|at91sam9x35ek|ariag25|	XX5"
COMPATIBLE_MACHINE	+= "|at91sam9rlek"

DEFAULT_PREFERENCE			= "-1"
DEFAULT_PREFERENCE_at91sam9260ek	= "2"
DEFAULT_PREFERENCE_at91sam9261ek	= "2"
DEFAULT_PREFERENCE_at91sam9263ek	= "2"
DEFAULT_PREFERENCE_at91sam9g10ek	= "2"
DEFAULT_PREFERENCE_at91sam9g20ek	= "2"
DEFAULT_PREFERENCE_at91sam9g20ek_2mmc	= "2"
DEFAULT_PREFERENCE_at91sam9g45ek	= "2"
DEFAULT_PREFERENCE_at91sam9g45ekes	= "2"
DEFAULT_PREFERENCE_at91sam9m10ekes	= "2"
DEFAULT_PREFERENCE_at91sam9m10g45ek	= "2"
DEFAULT_PREFERENCE_at91sam9rlek		= "2"
DEFAULT_PREFERENCE_at91sam9xeek		= "2"
DEFAULT_PREFERENCE_at91sam9g15ek	= "2"
DEFAULT_PREFERENCE_at91sam9g25ek	= "2"
DEFAULT_PREFERENCE_ariag25		= "2"
DEFAULT_PREFERENCE_at91sam9g35ek	= "2"
DEFAULT_PREFERENCE_at91sam9x25ek	= "2"
DEFAULT_PREFERENCE_at91sam9x35ek	= "2"

TARGET_LDFLAGS = ""

inherit base

S = "${WORKDIR}/u-boot-${PV}"

SRC_URI = "ftp://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2 \
	ftp://ftp.linux4sam.org/pub/uboot/u-boot-v2010.06/u-boot-5series_1.0.patch;apply=yes;name=at91exp \
	file://0001-Increase-malloc-area-512-kB.patch \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[md5sum] = "cd42bc64b6edafa6930ce299a144503e"
SRC_URI[sha256sum] = "790ccb12d99fc527a8b8d20dfdf491795d30f87aa0902f8cbda196583aa20bc8"
SRC_URI[at91exp.md5sum] = "544c71e11a008d914dc507e04c5b7e95"
SRC_URI[at91exp.sha256sum] = "691ba4cba126c2e409f38a0ef8a97d696bbde09804e5c0c9df4cb1ae40243f78"

