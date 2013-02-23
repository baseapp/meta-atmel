DESCRIPTION = "Linux kernel for AT91 processors"
PR = "r0"

require linux.inc

S = "${WORKDIR}/linux-${PV}"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "at91sam9g15ek|at91sam9g25ek|at91sam9g35ek|at91sam9x25ek|at91sam9x35ek"

#automatically get the git commit ID with AUTOREV
PV = "3.6.9"
SRCREV_pn-${PN} = "${AUTOREV}"

#FILESPATH =. "${FILE_DIRNAME}/linux-3.0:${FILE_DIRNAME}/linux-3.0/${MACHINE}:"

SRC_URI = " \
	git://github.com/linux4sam/linux-at91.git;protocol=git \
	"

#SRC_URI = " \
#	file://oe-core/setup-scripts/build/tmp-angstrom_v2012_05-eglibc/work/#at91sam9x35ek-angstrom-linux-gnueabi/linux-3.6.9-r0/git
#	\
#	"

#worked first time then failed not sure why
#SRC_URI_append = " \
#    	file://temp-kernel.patch;apply=yes \
#	"

SRC_URI_append_at91sam9x35ek = " \
	file://defconfig \
	"

S = "${WORKDIR}/git"
