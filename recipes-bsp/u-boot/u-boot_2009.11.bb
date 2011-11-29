DESCRIPTION = "U-boot for the AT91 Series" 
HOMEPAGE = "www.linux4sam.org"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM_pn-${PN} = "file://COPYING;md5=4c6cde5df68eff615d36789dc18edd3b"

PR = "r0"

PV = "2009.11"

require u-boot.inc

# To build u-boot for your machine, provide the following lines in your machine
# config, replacing the assignments as appropriate for your machine.
# UBOOT_MACHINES = "at91sam9g35ek at91sam9g35ek_dataflash at91sam9g35eknandflash"
#?UBOOT_ENTRYPOINT = "0x80008000"
#?UBOOT_LOADADDRESS = "0x80008000"

COMPATIBLE_MACHINE	 = "at91sam9260ek|at91sam9xeek|at91sam9g20ek|at91sam9g20ek_2mmc|		XX1"
COMPATIBLE_MACHINE	+= "|at91sam9261ek|at91sam9g10ek|						XX2"
COMPATIBLE_MACHINE	+= "|at91sam9263ek|								XX3"
COMPATIBLE_MACHINE	+= "|at91sam9g45ek|at91sam9g45ekes|at91sam9m10ekes|at91sam9m10g45ek|		XX4"
# COMPATIBLE_MACHINE	+= "|at91sam9g15ek|at91sam9g25ek|at91sam9g35ek|at91sam9x25ek|at91sam9x35ek|	XX5"
COMPATIBLE_MACHINE	+= "|at91sam9rlek"

DEFAULT_PREFERENCE			= "-1"
DEFAULT_PREFERENCE_at91sam9260ek	= "2"
DEFAULT_PREFERENCE_at91sam9261ek	= "2"
DEFAULT_PREFERENCE_at91sam9263ek	= "2"
DEFAULT_PREFERENCE_at91sam9g10ek	= "2"
DEFAULT_PREFERENCE_at91sam9g20ek	= "2"
DEFAULT_PREFERENCE_at91sam9g20ek_2mmc	= "2"
DEFAULT_PREFERENCE_at91sam9g45ek	= "3"
DEFAULT_PREFERENCE_at91sam9g45ekes	= "3"
DEFAULT_PREFERENCE_at91sam9m10ekes	= "3"
DEFAULT_PREFERENCE_at91sam9m10g45ek	= "3"
DEFAULT_PREFERENCE_at91sam9rlek		= "2"
DEFAULT_PREFERENCE_at91sam9xeek		= "2"
# DEFAULT_PREFERENCE_at91sam9g15ek	= "2"
# DEFAULT_PREFERENCE_at91sam9g25ek	= "2"
# DEFAULT_PREFERENCE_at91sam9g35ek	= "2"
# DEFAULT_PREFERENCE_at91sam9x25ek	= "2"
# DEFAULT_PREFERENCE_at91sam9x35ek	= "2"

TARGET_LDFLAGS = ""

inherit base

S = "${WORKDIR}/u-boot-${PV}"

SRC_URI = "ftp://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2 "

SRC_URI_append_at91 = "\
	file://at91/0002-Support-running-ATSAM9G45-M10-from-dataflash.patch \
	file://at91/0003-Update-SAM9M10-G45-config-for-dataflash-support.patch \
	file://at91/0004-Support-selecting-SPI-mode-in-dataflash-driver.patch \
	file://at91/0005-mux-replace-verbose-dataflash_mmc_mux-command.patch \
	file://at91/0006-libarm-board.c-Gets-overwritten-workaround.patch \
	file://at91/0007-fat.c-Add-DEBUG-currently-disabled.patch \
	file://at91/0008-env_dataflash.c-More-robust-handling.patch \
	file://at91/0009-cmd_debug.c-Add-a-generic-function-for-debug-vars.patch \
	file://at91/0010-debug.h-Add-header-for-debug-variables.patch \
	file://at91/0011-common-Makefile-Add-cmd_debug.c-to-build.patch \
	file://at91/0012-cmd_mci.c-Support-writing-out-AT91-mci-config.patch \
	file://at91/0013-atmel_dataflash.c-Status-printout-depend-on-DEBUG.patch \
	file://at91/0014-AT91-MCI-Add-support-for-SD-Card.patch \
	file://at91/0015-sam9m10g45ek-Add-configuration-file.patch \
	file://at91/0016-SupportEnv-load-from-SD-Card.patch \
	file://at91/0017-SD-Card-boot-patch-for-SAM9M10-G45.patch \
	file://at91/0018-ADD-AT91-Build-script.patch \
	file://at91/0019-Add-to-.gitignore.patch \
	file://at91/0020-Update-build-script.patch \
	file://at91/0021-Makefile-add-at91sam9g20ek_2mmc.patch \
	file://at91/0022-sam920ek_2mmc-Add-support.patch \
	file://at91/0023-sam9m10g45ek-sd-card-boot-environment.patch \
	file://at91/0024-Minor-fixes-to-_2mmc-support.patch \
	file://at91/0026-Revert-bad-sam9m10g45_devices.c-patch.patch \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[md5sum] = "d94700614225f53c853dfe714eb5fa47"
SRC_URI[sha256sum] = "066615314fc90a314823ac975ca2a525a51fdad41802f4088a3a21ce073e8de6"

