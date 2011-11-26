require linux.inc

DESCRIPTION = "Linux kernel for Atmel processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "at91sam9g35ek"

PV = "3.0.10"
# v3.0.10 tag
SRCREV_pn-${PN} = "c7e2ea59cdd74342d3614bee9fc42fa2fb5add7e"

# The main PR is now using MACHINE_KERNEL_PR
MACHINE_KERNEL_PR_append = "a"

FILESPATH =. "${FILE_DIRNAME}/linux-3.0:${FILE_DIRNAME}/linux-3.0/${MACHINE}:"

SRC_URI += "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-3.0.y;protocol=git \
            file://defconfig"

SRC_URI_append_at91 = " file://logo_linux_clut224.ppm \
"

S = "${WORKDIR}/git"

