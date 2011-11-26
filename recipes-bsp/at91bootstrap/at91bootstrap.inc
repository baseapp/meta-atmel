DESCRIPTION = "at91bootstrap bootloader loader"
SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2+"

PARALLEL_MAKE = ""

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} DESTDIR=${DEPLOY_DIR_IMAGE}/at91bootstrap"

AT91BOOTSTRAP_MACHINES ?= "${MACHINE}"

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	rm -fr ${S}/binaries
	install -d ${DEPLOY_DIR_IMAGE}/at91bootstrap
	for board in ${AT91BOOTSTRAP_MACHINES} ; do
		oe_runmake mrproper
		filename=`find board -name ${board}_defconfig`
		if ! [ "x$filename" == "x" ] ; then
			oe_runmake ${board}_defconfig
			oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}"
			oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}" boot
			oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}" host-utilities
			oe_runmake AT91_CUSTOM_FLAGS="${AT91BOOTSTRAP_FLAGS}" install
		else
			echo "${board} could not be built"
		fi
	done
}

FILES_${PN} = "/boot"

