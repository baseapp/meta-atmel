require linux.inc

COMPATIBLE_MACHINE = "at91"

DESCRIPTION = "Linux kernel for Atmel processors"
KERNEL_IMAGETYPE = "uImage"

DEFAULT_PREFERENCE = "-99"

PV = "3.0+3.1rc"
SRCREV_pn-${PN} = "9e79e3e9dd9672b37ac9412e9a926714306551fe"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "e"

SRC_URI += "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git \
            file://defconfig"



SRC_URI_append_at91 = " file://logo_linux_clut224.ppm \
"

S = "${WORKDIR}/git"

